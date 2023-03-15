package edu.huflit.hres_management.API.model;

public class RegisterStaffRequest {
    private String restaurantID, userName, password, role;
    public RegisterStaffRequest(String restaurantID, String uerName, String password, String role) {
        this.restaurantID = restaurantID;
        this.userName = uerName;
        this.password = password;
        this.role = role;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
