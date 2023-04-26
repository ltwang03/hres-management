package edu.huflit.hres_management.API.model;

import java.util.ArrayList;

import edu.huflit.hres_management.Model.Food;

public class GetFoodResponse {
    private String status;
    private ArrayList<Food> food;
    public GetFoodResponse(String status, ArrayList<Food> food) {
        this.status = status;
        this.food = food;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }
}
