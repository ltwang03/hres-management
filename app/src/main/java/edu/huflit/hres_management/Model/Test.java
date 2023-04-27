package edu.huflit.hres_management.Model;

public class Test {
    private String title;
    private String description;
    private boolean isAltLayout;

    public Test(String title, String description, boolean isAltLayout) {
        this.title = title;
        this.description = description;
        this.isAltLayout = isAltLayout;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAltLayout() {
        return isAltLayout;
    }
}