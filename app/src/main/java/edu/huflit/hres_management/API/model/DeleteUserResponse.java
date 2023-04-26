package edu.huflit.hres_management.API.model;

public class DeleteUserResponse {
    private String status;
    public DeleteUserResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
