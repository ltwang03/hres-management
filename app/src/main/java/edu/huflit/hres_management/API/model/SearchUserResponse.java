package edu.huflit.hres_management.API.model;

import java.util.List;

import edu.huflit.hres_management.Model.Staff;

public class SearchUserResponse {
    private String message;
    List<Staff> staff;

    public String getQuery() {
        return message;
    }

    public void setQuery(String message) {
        this.message = message;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }
}
