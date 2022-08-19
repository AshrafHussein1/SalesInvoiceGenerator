package model;

public class InvoiceLine {
    private final String itemName;
    private final double  itemPrice;
    private final int count;

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
}
