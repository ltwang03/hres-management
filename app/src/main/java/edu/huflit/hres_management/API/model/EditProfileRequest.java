package edu.huflit.hres_management.API.model;

public class EditProfileRequest {
    private String fullName;
    private int phoneNumber;

    public EditProfileRequest() {
    }

    public EditProfileRequest(String fullName, int phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
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

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
