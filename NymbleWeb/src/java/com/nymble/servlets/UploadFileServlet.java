/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.util.AppConstants;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author user
 */
public class UploadFileServlet extends HttpServlet {

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
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            if (ServletFileUpload.isMultipartContent(request)) {
                try {
                    List list = servletFileUpload.parseRequest(request);
                    Iterator iterator = list.iterator();
                    File dir = new File(getServletContext().getRealPath(AppConstants.LOCAL_FOLDER));
                    String fileName = "";
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    while (iterator.hasNext()) {
                        FileItem fi = (FileItem) iterator.next();
                        if (!fi.isFormField()) {
                            fileName = fi.getName();
                            fileName = fileName.replaceAll(" ", "_");
                            fi.write(new File(dir, fileName));
                        }
                    }

                    response.sendRedirect("uploadapp.jsp?update=true");
                } catch (FileUploadException ex) {
                    response.sendRedirect("admin_home.jsp?update=fail");
                    Logger.getLogger(UploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    response.sendRedirect("admin_home.jsp?update=fail");
                    Logger.getLogger(UploadFileServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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
}
