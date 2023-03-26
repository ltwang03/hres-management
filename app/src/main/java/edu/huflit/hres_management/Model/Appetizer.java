package edu.huflit.hres_management.Model;

public class Appetizer {
    private int resourceId;
    private String name;
    private String describe;
    private int price;

    public Appetizer(int resourceId, String name, String describe, int price) {
        this.resourceId = resourceId;
        this.name = name;
        this.describe = describe;
        this.price = price;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
