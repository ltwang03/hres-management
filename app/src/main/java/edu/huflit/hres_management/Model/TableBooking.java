package edu.huflit.hres_management.Model;

public class TableBooking {
    private int numberTable;
    private int amountCustomer;
    private String timeCheckin;

    public TableBooking(int numberTable, int amountCustomer, String timeCheckin) {
        this.numberTable = numberTable;
        this.amountCustomer = amountCustomer;
        this.timeCheckin = timeCheckin;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }

    public int getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(int amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public String getTimeCheckin() {
        return timeCheckin;
    }

    public void setTimeCheckin(String timeCheckin) {
        this.timeCheckin = timeCheckin;
    }
}
