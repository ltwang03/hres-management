package edu.huflit.hres_management.Model;

public class TableBooking {
    private String numberTable;
    private String nameCustomer;
    private String  amountCustomer;
    private String timeCheckin;
    private boolean booked;

    public TableBooking(String numberTable, String amountCustomer, String timeCheckin, String nameCustomer , boolean booked) {
        this.numberTable = numberTable;
        this.amountCustomer = amountCustomer;
        this.timeCheckin = timeCheckin;
        this.nameCustomer = nameCustomer;
        this.booked = booked;
    }

    public String getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(String numberTable) {
        this.numberTable = numberTable;
    }

    public String getAmountCustomer() {
        return amountCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public void setAmountCustomer(String amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public String getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(String timeCheckin) {
        this.timeCheckin = timeCheckin;
    }
}
