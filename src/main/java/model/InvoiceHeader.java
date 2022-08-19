package model;

import java.util.ArrayList;

public class InvoiceHeader {
    private final int invoiceNum;
    private final String invoiceDate;
    private final String customerName;
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

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void addInvoiceLine(InvoiceLine invoiceLine)
    {
        this.invoiceLines.add(invoiceLine);
    }
}
