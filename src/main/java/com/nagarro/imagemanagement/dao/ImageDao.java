/*
* Class name: ImageDao
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
* Description: Image Database connection
*/
package com.nagarro.imagemanagement.dao;

import com.nagarro.imagemanagement.model.ImageData;

public interface ImageDao {
    public void saveImage(ImageData image);
}
