package controller;

import model.CurrentLoadedInvoices;
import model.FileOperations;
import view.AppFrame;

public class AppMain {
    public static void main(String[] args ) {
        // Read from the default files upon booting and update the model array list with the read data
        /* This part is commented due to review point #1 (the tables should be empty then when you press the load button the data will display on the tables)
        FileOperations.setInvoiceHeaderFilePath("./InvoiceHeader.csv");
        FileOperations.setInvoiceLineFilePath("./InvoiceLine.csv");
        CurrentLoadedInvoices.setInvoices(FileOperations.readFile());
        */

        // Start the application
        new AppFrame();
    }
}
