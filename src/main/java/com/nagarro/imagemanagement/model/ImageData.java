/*
* Class name: ImageData
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
* Description: Image data information
*/
package com.nagarro.imagemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private int imageId;
    @Lob
    @Column(name = "image")
    private byte[] image;
    @Column(name = "image_name")
    private String name;
    @Column(name = "image_size")
    private long size;
    @Column(name = "user_id")
    private int userId;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getImgId() {
        return imageId;
    }

    public void setImgId(int imgId) {
        this.imageId = imgId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
