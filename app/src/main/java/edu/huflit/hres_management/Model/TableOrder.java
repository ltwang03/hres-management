package edu.huflit.hres_management.Model;

public class TableOrder {
    private String numberTable;
    private String nameCustomer;
    private String  amountCustomer;
    private String timeCheckin;
    private boolean booked;

    public TableOrder(String numberTable, String nameCustomer, String amountCustomer, String timeCheckin, boolean booked) {
        this.numberTable = numberTable;
        this.nameCustomer = nameCustomer;
        this.amountCustomer = amountCustomer;
        this.timeCheckin = timeCheckin;
        this.booked = booked;
    }

    public String getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(String numberTable) {
        this.numberTable = numberTable;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAmountCustomer() {
        return amountCustomer;
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

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
