package model;

import java.util.ArrayList;

public class CurrentLoadedInvoices {
    private static ArrayList<InvoiceHeader> invoices = new ArrayList<>();

    public static ArrayList<InvoiceHeader> getInvoices() {
        return invoices;
    }

    public static void setInvoices(ArrayList<InvoiceHeader> invoices) {
        CurrentLoadedInvoices.invoices = invoices;
    }

    public static void deleteInvoice(int invoiceNum) {
        for(int index = 0 ; index < invoices.size() ; index++) {
            if(invoices.get(index).getInvoiceNum() == invoiceNum){
                invoices.remove(index);
                return;
            }
        }
    }

    public static void updateInvoice(InvoiceHeader newInvoice) {
        // If the app found the invoice number existing in the database, replace the old data
        for(int index = 0 ; index < invoices.size() ; index++) {
            if (newInvoice.getInvoiceNum() == invoices.get(index).getInvoiceNum()) {
                invoices.set(index,newInvoice);
                return;
            }
        }
        // If the invoice number is new, then add the invoice as a new invoice
        invoices.add(newInvoice);
    }

    public static InvoiceHeader getInvoiceByInvoiceNumber(int invoiceNumber) {
        for (InvoiceHeader invoice : invoices) {
            if (invoiceNumber == invoice.getInvoiceNum()) {
                return invoice;
            }
        }
        return null;
    }
}
