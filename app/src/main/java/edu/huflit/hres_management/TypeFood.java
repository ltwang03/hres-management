package edu.huflit.hres_management;

public class TypeFood {
    private int resourceId;
    private String name_type;

    public TypeFood(int resourceId, String name_type) {
        this.resourceId = resourceId;
        this.name_type = name_type;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }
}
