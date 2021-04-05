/*
* Class name: HibernateConnection
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
* Last updated Date: 05/Mar/2021
*
* Description: Used for creating connection with database
*/
package com.nagarro.imagemanagement.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nagarro.imagemanagement.model.ImageData;
import com.nagarro.imagemanagement.model.User;

public class HibernateConnection {
    /**
     * @return session
     */
    public Session getConnection() {
        Configuration configuration = new Configuration().configure().addAnnotatedClass(User.class)
                .addAnnotatedClass(ImageData.class);
        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        return session;
    }
}
