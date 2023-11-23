package edu.huflit.hres_management.API.model;

import java.util.List;

import edu.huflit.hres_management.Model.Customer;

public class GetCustomerResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    private List<Customer> customer;

}
