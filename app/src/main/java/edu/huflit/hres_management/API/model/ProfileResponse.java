package edu.huflit.hres_management.API.model;

public class ProfileResponse {
    private String restaurantID, userName, role, fullName;
    private int phoneNumber;
    public ProfileResponse(String restaurantID, String userName, String role, String fullName, int phoneNumber) {
        this.restaurantID = restaurantID;
        this.userName = userName;
        this.role = role;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }
    public ProfileResponse() {}

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
