/*
* Class name: ImageService
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
package com.nagarro.imagemanagement.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ImageService {
    /**
     * delete image
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    /**
     * edit image data
     * 
     * @param request
     * @param response
     * @throws ServletException
     */
    public void editImage(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
