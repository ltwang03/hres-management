package edu.huflit.hres_management.API.model;

import java.util.List;

import edu.huflit.hres_management.Model.Staff;

public class UserResponse {
    private String status;
    private List<Staff> user;
    public UserResponse(String message, List<Staff> user) {
        this.status = status;
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Staff> getUser() {
        return user;
    }

    public void setUser(List<Staff> user) {
        this.user = user;
    }
}
