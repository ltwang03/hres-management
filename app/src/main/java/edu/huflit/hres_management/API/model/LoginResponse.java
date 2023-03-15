package edu.huflit.hres_management.API.model;

public class LoginResponse {
    private String status, message, role, userName, token;
    public LoginResponse(String status, String message, String role, String userName, String token) {
        this.status = status;
        this.message = message;
        this.role = role;
        this.userName = userName;
        this.token = token;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
