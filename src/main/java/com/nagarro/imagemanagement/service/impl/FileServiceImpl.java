/*
* Class name: FileServiceImpl
*
* Version info: jdk 1.8
*
* Copyright notice:
* 
* Author info: Arpit Garg
*
* Creation date: 01/Apr/2021
*
* Last updated By: Arpit Garg
*
* Last updated Date: 05/Apr/2021
*
* Description: Save image file to database
*/
package com.nagarro.imagemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.nagarro.imagemanagement.dao.impl.ImageDaoImpl;
import com.nagarro.imagemanagement.model.ImageData;
import com.nagarro.imagemanagement.service.FileService;

@MultipartConfig
public class FileServiceImpl extends HttpServlet implements FileService {
    private static final String LOGIN_SUCCESS_JSP = "login-success.jsp";
    private static final String UPLOADEDIMAGE = "uploadedimage";
    private static final String USER_ID = "userId";
    private static final String TEXT_HTML_CHARSET_UTF_8 = "text/html;charset=UTF-8";
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType(TEXT_HTML_CHARSET_UTF_8);
        saveImage(request, response);
    }

    /**
     * save image in the database
     * 
     * @param request
     * @param response
     */
    public void saveImage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute(USER_ID);
        try (PrintWriter out = response.getWriter()) {
            Part filePart = request.getPart(UPLOADEDIMAGE);
            String fileName = filePart.getSubmittedFileName();
            InputStream input = null;
            if (filePart != null) {
                input = filePart.getInputStream();
            }
            byte[] byteArray = new byte[input.available()];
            input.read(byteArray);
            for (int i = 0; i < byteArray.length; i++) {
                System.out.print(byteArray[i]);
            }
            ImageData imageData = new ImageData();
            imageData.setUserId(userId);
            imageData.setImage(byteArray);
            imageData.setName(fileName);
            imageData.setSize(byteArray.length);
            new ImageDaoImpl().saveImage(imageData);
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_SUCCESS_JSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}