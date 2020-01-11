/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.bean.mgr.LoginBeanManager;
import com.nymble.beans.LoginBean;
import com.nymble.beans.UserPseudoBean;
import com.nymble.util.DecryptEncryptString;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class LoginTunnelServlet extends HttpServlet {

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
        //PrintWriter out = response.getWriter();
        String pseudonym = "";

        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());

            Object obj = ois.readObject();
            ois.close();

            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            if (obj instanceof LoginBean) {
                LoginBean loginBean = (LoginBean) obj;
                LoginBeanManager loginBeanManager = new LoginBeanManager();
                if (loginBean != null) {
                    System.out.println("Username : " + loginBean.getUsername() + " and  Password : " + loginBean.getPassword());
                    loginBean.setUserType(loginBeanManager.checkLogin(loginBean));

                    DecryptEncryptString decryptEncrypt = new DecryptEncryptString();
                    pseudonym = decryptEncrypt.encrypt(loginBean.getPseudonym(), loginBean.getPassword());
                    loginBeanManager.setPseudonym(loginBean, pseudonym);
                    loginBean.setPseudonym(pseudonym);


                    System.out.println("User Type : " + loginBeanManager.checkLogin(loginBean));
                    oos.writeObject(loginBean);
                    oos.flush();
                }

            }


            oos.writeObject("This is responce from servlet");
            oos.flush();


            oos.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginTunnelServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //out.close();
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
