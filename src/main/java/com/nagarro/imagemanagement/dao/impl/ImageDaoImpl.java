/*
* Class name: ImageDaoImpl
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
* Description: Image data save and delete
*/
package com.nagarro.imagemanagement.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nagarro.imagemanagement.dao.ImageDao;
import com.nagarro.imagemanagement.model.ImageData;
import com.nagarro.imagemanagement.utils.HibernateConnection;

public class ImageDaoImpl implements ImageDao {
    /**
     * Save image into database
     */
    public void saveImage(ImageData image) {
        Session session = new HibernateConnection().getConnection();
        Transaction transaction = session.beginTransaction();
        session.save(image);
        transaction.commit();
        session.close();
    }

    /**
     * delete an image from database
     * 
     * @param image
     */
    public void deleteImageData(ImageData image) {
        Session session = new HibernateConnection().getConnection();
        Transaction transaction = session.beginTransaction();
        session.delete(image);
        transaction.commit();
        session.close();
    }
}
