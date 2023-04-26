package edu.huflit.hres_management.Model;

public class Staff {
    private String fullName;
    private Number phoneNumber;

    private String userName;

    public Staff(String name, Number phoneNumber) {
        this.fullName = name;
        this.phoneNumber = phoneNumber;
    }

    public Staff(String name, Number phoneNumber, String userName) {
        this.fullName = name;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }

    public void setPhoneNumber(Number phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Number getPhoneNumber() {
        return phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
