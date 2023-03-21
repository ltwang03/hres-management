package edu.huflit.hres_management.API.model;

import java.util.List;

public class UserResponse {
    private String message;
    private List<String> user;
    public UserResponse(String message, List<String> user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }
}
