package edu.huflit.hres_management.API.model;

public class LoginRequest {
    private String restaurantID, userName, password;

    public LoginRequest(String restaurantID ,String userName, String password) {
        this.restaurantID = restaurantID;
        this.userName = userName;
        this.password = password;
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


}
