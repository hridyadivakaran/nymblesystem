/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.bean.mgr.RegisterBeanManager;
import com.nymble.beans.RegisterBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Staff
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */

            RegisterBean registerBean = new RegisterBean();
            registerBean.setUsername(request.getParameter("username"));
            registerBean.setPassword(request.getParameter("password"));
            registerBean.setFirstName(request.getParameter("first_name"));
            registerBean.setLastName(request.getParameter("last_name"));
            registerBean.setEmail(request.getParameter("email"));
            registerBean.setPhone(request.getParameter("phone"));
            registerBean.setState(request.getParameter("state"));
            registerBean.setDistrict(request.getParameter("district"));
            registerBean.setNationality(request.getParameter("nationality"));
            registerBean.setAddress(request.getParameter("address"));

            RegisterBeanManager registerBeanManager = new RegisterBeanManager();

            if (registerBeanManager.registerClient(registerBean)) {
                response.sendRedirect("?insert=true");
            } else {
                response.sendRedirect("register.jsp?insert=false");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("registration.jsp?insert=false");
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("registration.jsp?insert=false");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private byte[] createKey(String ip) {
        byte[] key = new byte[128];
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] data = digest.digest(ip.getBytes());
            key = Arrays.copyOf(data, 16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return key;
    }
}
