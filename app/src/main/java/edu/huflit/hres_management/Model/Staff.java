package edu.huflit.hres_management.Model;

public class Staff {
    private String fullName;
    private Number phoneNumber;

    public Staff(String name, Number phoneNumber) {
        this.fullName = name;
        this.phoneNumber = phoneNumber;
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



    public void getFullName(String fullName) {
        this.fullName = fullName;
    }

}
