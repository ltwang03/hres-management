package edu.huflit.hres_management.Model;

public class Customer {
    private String name;
    private String phone;
    private String _id;

    public String getPhone() {
        return phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
