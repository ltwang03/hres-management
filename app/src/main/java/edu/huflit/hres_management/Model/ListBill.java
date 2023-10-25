package edu.huflit.hres_management.Model;

public class ListBill {

    private String tableNumber ;
    private String customerName;
    private String amountCustomer;
    private String checkinTime;



    public ListBill(String tableNumber , String customerName , String amountCustomer , String checkinTime) {

        this.tableNumber = tableNumber;
        this.customerName = customerName;
        this.amountCustomer = amountCustomer;
        this.checkinTime = checkinTime;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAmountCustomer() {
        return amountCustomer;
    }

    public void setAmountCustomer(String amountCustomer) {
        this.amountCustomer = amountCustomer;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }
}
