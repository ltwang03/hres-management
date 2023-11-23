package edu.huflit.hres_management.API.model;

public class DeleteCustomerRequest {
    private String _id;

    public DeleteCustomerRequest(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }
}
