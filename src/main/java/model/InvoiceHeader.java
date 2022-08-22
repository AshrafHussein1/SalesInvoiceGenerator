package model;

import java.util.ArrayList;

public class InvoiceHeader {
    private final int invoiceNum;
    private String invoiceDate;
    private String customerName;
    private final ArrayList<InvoiceLine> invoiceLines;

    public InvoiceHeader(int num,String date,String name) {
        this.invoiceNum = num;
        this.invoiceDate = date;
        this.customerName = name;
        this.invoiceLines = new ArrayList<>();
    }

    public int getInvoiceNum() {
        return this.invoiceNum;
    }

    public String getInvoiceDate() {
        return this.invoiceDate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public void setInvoiceDate(String date) {
        this.invoiceDate = date;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void addInvoiceLine(InvoiceLine invoiceLine)
    {
        this.invoiceLines.add(invoiceLine);
    }

    public void deleteItem(int index) {
        this.invoiceLines.remove(index);
    }

    public void updateItem(int itemIndex,String name,double price, int count) {
        this.invoiceLines.get(itemIndex).updateItem(name,price,count);
    }
}
