package edu.huflit.hres_management.Model;

import java.util.List;

public class Bill {
    private String nameCustomer;
    private String tableNumber;
    private String timeCheckIn;
    private String amountCustomer;
    private List<FoodBill> listFoodBill;

    public Bill(String tableNumber, String timeCheckIn, String nameCustomer, String amountCustomer) {
        this.nameCustomer = nameCustomer;

        this.tableNumber = tableNumber;
        this.timeCheckIn = timeCheckIn;
        this.amountCustomer = amountCustomer;

    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getTimeCheckIn() {
        return timeCheckIn;
    }

    public void setTimeCheckIn(String timeCheckIn) {
        this.timeCheckIn = timeCheckIn;
    }

    public String getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(String amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

}