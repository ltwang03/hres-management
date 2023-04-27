package edu.huflit.hres_management.Model;

public class Food {
    String resourceID, name, category, describe, price;
    Integer product_id;
    public Food(Integer product_id  ,String resourceID, String name, String category, String describe, String price) {
        this.product_id = product_id;
        this.resourceID = resourceID;
        this.name = name;
        this.category = category;
        this.describe = describe;
        this.price = price;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer id) {
        this.product_id = id;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
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
