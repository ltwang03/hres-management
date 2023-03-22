package edu.huflit.hres_management.API.model;

public class RegisterManagerRequest {
    private String restaurantID, userName, password, role, fullName;
    private Number phoneNumber;
    public RegisterManagerRequest(String restaurantID, String uerName, String password, String role, String fullName, Number phoneNumber) {
       this.restaurantID = restaurantID;
       this.userName = uerName;
       this.fullName = fullName;
       this.phoneNumber = phoneNumber;
       this.password = password;
       this.role = role;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Number getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Number phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
