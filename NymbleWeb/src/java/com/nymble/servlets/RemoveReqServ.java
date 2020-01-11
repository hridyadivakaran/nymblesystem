/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.bean.mgr.RequestBeanManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class RemoveReqServ extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String block_id = "";
        try {
            /* TODO output your page here 
             */

            String reqid = request.getParameter("id");
            block_id = request.getParameter("block_id");
            RequestBeanManager requestBeanManager = new RequestBeanManager();
            requestBeanManager.removeReq(reqid);
            response.sendRedirect("view_reqs.jsp?block_id=" + block_id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RemoveReqServ.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("view_reqs.jsp?block_id=" + block_id);
        } catch (SQLException ex) {
            Logger.getLogger(RemoveReqServ.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("view_reqs.jsp?block_id=" + block_id);
        } catch (NullPointerException ex) {
            Logger.getLogger(RemoveReqServ.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("view_reqs.jsp?block_id=" + block_id);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
