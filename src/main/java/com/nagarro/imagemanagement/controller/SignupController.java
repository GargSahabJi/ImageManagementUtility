/*
* Class name: SignupController
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
* Description: Signup control for user
*/
package com.nagarro.imagemanagement.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imagemanagement.dao.impl.UserDaoImpl;
import com.nagarro.imagemanagement.model.User;

public class SignupController extends HttpServlet {
    private static final String LOGIN_JSP = "login.jsp";
    private static final String PASSWORD2 = "password";
    private static final String USERNAME2 = "username";
    private static final String LASTNAME2 = "lastname";
    private static final String FIRSTNAME2 = "firstname";
    private static final String SIGN_UP_JSP = "SignUp.jsp";

    private static final long serialVersionUID = 1L;
    User createUser = null;

    public void init() {
        createUser = new User();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(SIGN_UP_JSP);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            addNewUser(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewUser(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter(FIRSTNAME2);
        String lastName = request.getParameter(LASTNAME2);
        String userName = request.getParameter(USERNAME2);
        String password = request.getParameter(PASSWORD2);
        createUser.setFirstName(firstName);
        createUser.setLastName(lastName);
        createUser.setUsername(userName);
        createUser.setPassword(password);
        new UserDaoImpl().saveUser(createUser);
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_JSP);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
