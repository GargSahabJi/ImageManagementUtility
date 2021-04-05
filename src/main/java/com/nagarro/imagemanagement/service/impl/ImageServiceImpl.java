/*
* Class name: ImageServiceImpl
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
* Description: delete and edit image data
*/
package com.nagarro.imagemanagement.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.imagemanagement.dao.impl.ImageDaoImpl;
import com.nagarro.imagemanagement.model.ImageData;
import com.nagarro.imagemanagement.utils.HibernateConnection;

@MultipartConfig
public class ImageServiceImpl extends HttpServlet {
    private static final String UPLOADEDNEWIMAGE = "uploadednewimage";
    private static final String NEW_NAME = "new-name";
    private static final String IMAGE_UPDATE_ID = "imageUpdateId";
    private static final String USER_ID = "userId";
    private static final String IMAGE_ID_FOR_EDIT = "imageIdForEdit";
    private static final String LOGIN_SUCCESS_JSP = "login-success.jsp";
    private static final String DELETE_BUTTON = "deleteButton";
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter(DELETE_BUTTON) != null) {
            deleteImage(request, response);
        }
        if (request.getParameter(IMAGE_ID_FOR_EDIT) != null) {
            editImage(request, response);
        }
    }

    /**
     * delete the image
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imgId = (String) request.getParameter(DELETE_BUTTON);
        System.out.println(imgId);
        int imageId = Integer.parseInt(imgId);
        ImageData imageData = new ImageData();
        imageData.setImgId(imageId);
        new ImageDaoImpl().deleteImageData(imageData);
        RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_SUCCESS_JSP);
        dispatcher.forward(request, response);
    }

    /**
     * edit the image
     * 
     * @param request
     * @param response
     * @throws ServletException
     */
    public void editImage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String imageId = request.getParameter(IMAGE_UPDATE_ID).trim();
        int id = Integer.parseInt(imageId);
        String newName = request.getParameter(NEW_NAME).trim();
        try (PrintWriter out1 = response.getWriter()) {
            Part filePart = request.getPart(UPLOADEDNEWIMAGE);
            InputStream input = null;
            if (filePart != null) {
                input = filePart.getInputStream();
            }
            byte[] byteArray = new byte[input.available()];
            input.read(byteArray);
            for (int i = 0; i < byteArray.length; i++) {
                System.out.print(byteArray[i]);
            }
            Session hibernateSession = new HibernateConnection().getConnection();
            ImageData imageData = new ImageData();
            imageData = hibernateSession.get(ImageData.class, id);
            if (imageData.getName() != newName) {
                imageData.setName(newName);
            }
            if (!Arrays.equals(byteArray, imageData.getImage()) && (byteArray.length != 0)) {
                imageData.setImage(byteArray);
                imageData.setSize(byteArray.length);
            } else {
                imageData.setImage(imageData.getImage());
                imageData.setSize(imageData.getSize());
                imageData.setUserId(imageData.getUserId());
            }
            Transaction transaction = hibernateSession.beginTransaction();
            hibernateSession.update(imageData);
            transaction.commit();
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_SUCCESS_JSP);
            dispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
