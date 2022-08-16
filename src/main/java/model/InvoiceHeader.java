package model;

import java.util.ArrayList;

public class InvoiceHeader {
    private int invoiceNum;
    private String invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> invoiceLines;

    public InvoiceHeader() {
        invoiceNum = -1;
        invoiceDate = "";
        customerName = "";
        invoiceLines = new ArrayList<InvoiceLine>();
    }

    public InvoiceHeader(int num,String date,String name) {
        this.invoiceNum = num;
        this.invoiceDate = date;
        this.customerName = name;
        this.invoiceLines = new ArrayList<InvoiceLine>();
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
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

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    public void addInvoiceLine(InvoiceLine invoiceLine)
    {
        this.invoiceLines.add(invoiceLine);
    }
}
