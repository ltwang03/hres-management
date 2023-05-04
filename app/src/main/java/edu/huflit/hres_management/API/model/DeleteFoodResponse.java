package edu.huflit.hres_management.API.model;

public class DeleteFoodResponse {
    String status;
    public DeleteFoodResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
