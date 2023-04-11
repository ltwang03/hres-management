package edu.huflit.hres_management.Model;

import java.util.List;

public class Bill {
    private String nameCustomer;
    private String timeCheckout;
    private String priceTotal;
    private List<FoodBill> listFoodBill;

    public Bill(String nameCustomer, String timeCheckout, String priceTotal, List<FoodBill> listFoodBill) {
        this.nameCustomer = nameCustomer;
        this.timeCheckout = timeCheckout;
        this.priceTotal = priceTotal;
        this.listFoodBill = listFoodBill;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getTimeCheckout() {
        return timeCheckout;
    }

    public void setTimeCheckout(String timeCheckout) {
        this.timeCheckout = timeCheckout;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public List<FoodBill> getListFoodBill() {
        return listFoodBill;
    }

    public void setListFoodBill(List<FoodBill> listFoodBill) {
        this.listFoodBill = listFoodBill;
    }
}
