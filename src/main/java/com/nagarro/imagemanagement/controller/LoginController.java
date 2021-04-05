/*
* Class name: LoginController
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
* Description: Login control and validation for user
*/
package com.nagarro.imagemanagement.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.imagemanagement.dao.impl.UserDaoImpl;

public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String LOGIN_SUCCESS_JSP = "login-success.jsp";
    private static final String PASSWORD2 = "password";
    private static final String USERNAME2 = "username";
    private static final String LOGIN_JSP = "login.jsp";
    private UserDaoImpl loginDao;

    public void init() {
        loginDao = new UserDaoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(LOGIN_JSP);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Validate the user name and password
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter(USERNAME2);
        String password = request.getParameter(PASSWORD2);
        int userId = loginDao.validateUser(username, password);
        if (userId != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", userId);
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_SUCCESS_JSP);
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(request, response);
        }
    }
}