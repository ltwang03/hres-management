package edu.huflit.hres_management.Model;

public class FoodBill {
    private int ordinalFood;
    private String nameFood;
    private int amountFood;
    private int totalFood;

    public FoodBill(int ordinalFood, String nameFood, int amountFood, int totalFood) {
        this.ordinalFood = ordinalFood;
        this.nameFood = nameFood;
        this.amountFood = amountFood;
        this.totalFood = totalFood;
    }

    public int getOrdinalFood() {
        return ordinalFood;
    }

    public void setOrdinalFood(int ordinalFood) {
        this.ordinalFood = ordinalFood;
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

    public int getTotalFood() {
        return totalFood;
    }

    public void setTotalFood(int totalFood) {
        this.totalFood = totalFood;
    }
}
