package edu.huflit.hres_management.Model;

public class Food {
    String resourceID, name, category, describe, price;
    public Food(String resourceID, String name, String category, String describe, String price) {
        this.resourceID = resourceID;
        this.name = name;
        this.category = category;
        this.describe = describe;
        this.price = price;
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
