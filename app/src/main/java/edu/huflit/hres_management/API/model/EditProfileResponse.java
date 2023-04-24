package edu.huflit.hres_management.API.model;

public class EditProfileResponse {
    private String status;

    public EditProfileResponse() {
    }

    public EditProfileResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
