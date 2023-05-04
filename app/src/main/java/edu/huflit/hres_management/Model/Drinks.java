package edu.huflit.hres_management.Model;

public class Drinks {
    private String resourceId;
    private String name;
    private String category;
    private String describe;
    private String price;
    private Integer id;


    public Drinks(Integer id,String resourceId, String name, String category, String describe, String price) {
        this.resourceId = resourceId;
        this.name = name;
        this.category = category;
        this.describe = describe;
        this.price = price;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
