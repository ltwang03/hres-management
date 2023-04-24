package edu.huflit.hres_management.API.model;

public class AddFoodResponse {
    private String status;

    public AddFoodResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
