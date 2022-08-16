package model;

public class InvoiceLine {
    private String itemName;
    private double  itemPrice;
    private int count;

    public InvoiceLine(){
        this.itemName = "";
        this.itemPrice = 0;
        this.count = 0;
    }

    public InvoiceLine(String name,double price,int count){
        this.itemName = name;
        this.itemPrice = price;
        this.count = count;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItemName() {
        return this.itemName;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public int getCount() {
        return this.count;
    }
}
