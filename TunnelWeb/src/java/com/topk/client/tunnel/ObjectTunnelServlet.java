/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.topk.client.tunnel;

import com.topk.bean.LoginBean;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harris Bastin
 */
public class ObjectTunnelServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/octect-stream");

        try {

            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            Object readObject = ois.readObject();
            /* This is not recommented*/
            //LoginBean loginBean = (LoginBean) readObject;
            //  System.out.println("Username : " + loginBean.getUsername());
            //  System.out.println("Password : " + loginBean.getPassword());
            if (readObject instanceof LoginBean) {
                LoginBean loginBean = (LoginBean) readObject;
                System.out.println("Username : " + loginBean.getUsername());
                System.out.println("Password : " + loginBean.getPassword());
            }
            oos.writeObject("this is resp from Server");
            oos.flush();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObjectTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
