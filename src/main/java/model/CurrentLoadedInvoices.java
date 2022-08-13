package model;

import java.util.ArrayList;

public class CurrentLoadedInvoices {
    private static ArrayList<InvoiceHeader> invoices = new ArrayList<InvoiceHeader>();

    public static ArrayList<InvoiceHeader> getInvoices() {
        return invoices;
    }

    public static void setInvoices(ArrayList<InvoiceHeader> invoices) {
        CurrentLoadedInvoices.invoices = invoices;
    }

    public static void addNewInvoice(InvoiceHeader invoice) { }

    public static void deleteInvoice(int invoiceNum) {}

    public static void updateInvoice(int invoiceNum,InvoiceHeader newInvoice) {}

}
