package edu.huflit.hres_management.API.model;

public class DeleteFoodRequest {
    String product_id;
    public DeleteFoodRequest(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
