package edu.huflit.hres_management.API.model;

public class AddFoodRequest {
    private String resourceID;
    private String name;
    private String category;
    private String describe;
    private String  price;

    AddFoodRequest() {
    }
    public AddFoodRequest(String resourceID, String name, String price,String category, String describe) {
        this.resourceID = resourceID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.describe = describe;
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
