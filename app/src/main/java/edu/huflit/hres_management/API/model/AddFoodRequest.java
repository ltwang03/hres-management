package edu.huflit.hres_management.API.model;

public class AddFoodRequest {
    private int product_id;
    private String resourceID;
    private String name;
    private String category;
    private String describe;
    private String  price;

    AddFoodRequest() {
    }
    public AddFoodRequest(int product_id,String resourceID, String name, String price,String category, String describe) {
        this.product_id = product_id;
        this.resourceID = resourceID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.describe = describe;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getResourceId() {
        return resourceID;
    }

    public void setResourceId(String resourceId) {
        this.resourceID = resourceId;
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
