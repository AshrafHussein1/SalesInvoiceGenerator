package model;

public class InvoiceLine {
    private String itemName;
    private double  itemPrice;
    private int count;

    public InvoiceLine(String name,double price,int count){
        this.itemName = name;
        this.itemPrice = price;
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

    public void updateItem(String name,double price, int count) {
        this.count = count;
        this.itemPrice = price;
        this.itemName = name;
    }
}
