package edu.huflit.hres_management.Model;

public class FoodBill {
    private int priceFood;
    private String nameFood;
    private int amountFood;

    public FoodBill(int priceFood, String nameFood, int amountFood) {
        this.priceFood = priceFood;
        this.nameFood = nameFood;
        this.amountFood = amountFood;
    }

    public int getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(int priceFood) {
        this.priceFood = priceFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getAmountFood() {
        return amountFood;
    }

    public void setAmountFood(int amountFood) {
        this.amountFood = amountFood;
    }

}
