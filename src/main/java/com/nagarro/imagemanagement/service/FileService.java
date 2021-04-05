/*
* Class name: FileService
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
package com.nagarro.imagemanagement.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileService {
    /**
     * save image
     * 
     * @param request
     * @param response
     * @throws ServletException
     */
    public void saveImage(HttpServletRequest request, HttpServletResponse response) throws ServletException;
}
