package edu.huflit.hres_management.API.model;

public class DeleteUserRequest {
    private String userName;
    public DeleteUserRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
