/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.bean.mgr.LoginBeanManager;
import com.nymble.beans.LoginBean;
import com.nymble.util.AppConstants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class UpdateAccountServlet extends HttpServlet {

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
        String src = "";
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            HttpSession session = request.getSession();
            LoginBean lb = (LoginBean) session.getAttribute(AppConstants.USER_SESSION);
            LoginBean loginBean = new LoginBean();
            loginBean.setUsername(request.getParameter("username"));
            loginBean.setPassword(request.getParameter("password"));
            String newPassword = request.getParameter("new_password");
            src = request.getParameter("src");
            LoginBeanManager loginBeanManager = new LoginBeanManager();
            if (loginBeanManager.updateAccount(loginBean, newPassword)) {
                response.sendRedirect(src + "?insert=true");
            } else {
                response.sendRedirect(src + "?insert=false");

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect(src + "?insert=false");
        } catch (SQLException ex) {
            Logger.getLogger(UpdateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect(src + "?insert=false");
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
