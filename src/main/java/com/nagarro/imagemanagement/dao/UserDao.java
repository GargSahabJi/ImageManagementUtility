/*
* Class name: UserDao
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
* Description: User Database connection
*/
package com.nagarro.imagemanagement.dao;

import com.nagarro.imagemanagement.model.User;

public interface UserDao {
    /**
     * Save the user
     * 
     * @param user
     */
    public void saveUser(User user);

    /**
     * Validate the user
     * 
     * @param userName
     * @param password
     * @return
     */
    public int validateUser(String userName, String password);
}
