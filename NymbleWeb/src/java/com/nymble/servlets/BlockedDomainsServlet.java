      /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.bean.mgr.BlockedUserManager;
import com.nymble.beans.BlockedBean;
import com.nymble.beans.UserBean;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class BlockedDomainsServlet extends HttpServlet {

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
        try {
            ObjectInputStream ois = new ObjectInputStream(request.getInputStream());

            Object obj = ois.readObject();
            ois.close();

            ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
            if (obj instanceof UserBean) {
                UserBean userBean = (UserBean) obj;
                System.out.println("username : " + userBean.getUsername());
                System.out.println("mac : " + userBean.getMacAddress());
                System.out.println("domain : " + userBean.getDomain());
                BlockedUserManager blockedUserManager = new BlockedUserManager();
                ArrayList<String> domains = blockedUserManager.getAllBlockedDomainsFor(userBean);
                String notBlocked = "true ";
                for (int i = 0; i < domains.size(); i++) {
                    System.out.println("User domain : " + userBean.getDomain());
                    System.out.println("User domain (DB) : " + domains.get(i));
                    if (domains.get(i).contains(userBean.getDomain())) {
                        notBlocked = "false";
                        break;
                    }

                }


 
                oos.writeObject(notBlocked);
                oos.flush();
            }
            oos.writeObject("This is responce from servlet");
            oos.flush();


            oos.close();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BlockedDomainsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
