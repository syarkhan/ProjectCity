package com.example.sheryarkhan.projectcity;

import java.security.Timestamp;

/**
 * Created by ibrahim-01 on 7/17/2017.
 */

public class userClass {


    private String PrimaryLocation;
    private String ProfilePicture;
    private String Username;

    public userClass(String Username, long timestamp, String ProfilePicture, String userBio,String PrimaryLocation, double phoneNo, boolean status) {
        this.Username = Username;
        this.timestamp = timestamp;
        this.UserBio = userBio;
        this.ProfilePicture = ProfilePicture;
        this.phoneNo = phoneNo;
        this.status = status;
        this.PrimaryLocation = PrimaryLocation;
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


    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
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



    public userClass(String Username, double latitude, double longitude, double phoneNo, boolean status) {

        this.Username = Username;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNo = phoneNo;
        this.status = status;
    }

    public userClass(String Username, double latitude, double longitude ) {

        this.Username = Username;
        this.latitude = latitude;
        this.longitude = longitude;

    }



    public userClass(){

    }


    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        ProfilePicture = profilePicture;
    }

    public String getPrimaryLocation() {
        return PrimaryLocation;
    }

    public void setPrimaryLocation(String primaryLocation) {
        PrimaryLocation = primaryLocation;
    }
}
