package edu.huflit.hres_management.API.model;

public class UpdateFoodRequest {
    int product_id;
    String name, category, describe, price;
    public UpdateFoodRequest(int product_id, String name, String category, String describe, String price) {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.describe = describe;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
