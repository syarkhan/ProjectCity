package com.example.sheryarkhan.projectcity;

import java.security.Timestamp;

/**
 * Created by ibrahim-01 on 7/17/2017.
 */

public class userClass {


    private String fullName;

    public userClass(String fullName, long timestamp, long imageId, String userBio, double latitude, double longitude, double phoneNo, boolean status) {
        this.fullName = fullName;
        this.timestamp = timestamp;
        this.imageId = imageId;
        this.UserBio = userBio;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    private long timestamp;
    private long imageId;
    private String UserBio;
    private double latitude;
    private double longitude;
    private double phoneNo;
    private boolean status;


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getUserBio() {
        return UserBio;
    }

    public void setUserBio(String userBio) {
        UserBio = userBio;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(double phoneNo) {
        this.phoneNo = phoneNo;
    }



    public userClass(String fullName, double latitude, double longitude, double phoneNo, boolean status) {

        this.fullName = fullName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    public userClass(String fullName, double latitude, double longitude ) {

        this.fullName = fullName;
        this.latitude = latitude;
        this.longitude = longitude;

    }



    public userClass(){

    }




}
