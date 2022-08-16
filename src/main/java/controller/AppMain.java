package controller;

import model.CurrentLoadedInvoices;
import model.FileOperations;
import view.AppFrame;

public class AppMain {
    public static void main(String[] args ) {
        // Read from the default files upon booting and update the model array list with the read data
        FileOperations.setInvoiceHeaderFilePath("./InvoiceHeader.csv");
        FileOperations.setInvoiceLineFilePath("./InvoiceLine.csv");
        CurrentLoadedInvoices.setInvoices(FileOperations.readFile());
        // Start the application
        AppFrame appFrame = new AppFrame();
    }
}
