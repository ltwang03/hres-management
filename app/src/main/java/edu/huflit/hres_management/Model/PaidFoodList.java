package edu.huflit.hres_management.Model;

public class PaidFoodList {


    private String product_name;
    private Integer product_price;
    private Integer product_amount;


    public PaidFoodList(String product_name , Integer product_price , Integer product_amount)
    {


        this.product_name = product_name;
        this.product_amount = product_amount;
        this.product_price = product_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public Integer getProduct_amount() {
        return product_amount;
    }

    public void setProduct_amount(Integer product_amount) {
        this.product_amount = product_amount;
    }
}
