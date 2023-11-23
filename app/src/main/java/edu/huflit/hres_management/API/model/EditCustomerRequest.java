package edu.huflit.hres_management.API.model;

public class EditCustomerRequest {
    public EditCustomerRequest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

}
