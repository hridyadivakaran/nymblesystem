/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nymble.servlets;

import com.nymble.util.AppConstants;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class DownloadFileServlet extends HttpServlet {

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


        /*
         * TODO output your page here. You may use following sample code.
         */

     
            String fileName = request.getParameter("fileName");
            String path = getServletContext().getRealPath(AppConstants.LOCAL_FOLDER);
            String servLoc = path + File.separator + fileName;
            File selectedFile = new File(servLoc);
            if (selectedFile.exists()) {
                response.setContentType("application/octect-stream");
                response.setContentLength((int) selectedFile.length());
                response.setHeader("Content-Disposition", "attachment; filename=\"" + selectedFile.getName() + "\"");
                FileInputStream inputStream = new FileInputStream(selectedFile);
                BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                byte[] readData = new byte[1024];
                int readCount = -1;
                while ((readCount = inputStream.read(readData)) != -1) {
                    outputStream.write(readData, 0, readCount);
                }
                outputStream.flush();
                inputStream.close();
                outputStream.close();

            } else {

                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("path :" + path);

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
