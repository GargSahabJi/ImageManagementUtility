/*
* Class name: UserDaoImpl
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
* Description: User save and validation
*/
package com.nagarro.imagemanagement.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.imagemanagement.dao.UserDao;
import com.nagarro.imagemanagement.model.User;
import com.nagarro.imagemanagement.utils.HibernateConnection;

public class UserDaoImpl implements UserDao {

    /**
     * save user
     */
    public void saveUser(User user) {
        Session session = new HibernateConnection().getConnection();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    /**
     * validate the user
     * 
     * @param userName
     * @param password
     */
    public int validateUser(String userName, String password) {
        Transaction transaction = null;
        User user = null;
        try {
            Session session = new HibernateConnection().getConnection();
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User U WHERE U.username = :userName")
                    .setParameter("userName", userName).uniqueResult();
            if (user != null && user.getPassword().equals(password)) {
                return user.getId();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return 0;
    }
}