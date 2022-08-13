package model;

import java.util.ArrayList;

public class FileOperations {

    private static String invoiceHeaderFilePath;
    private static String invoiceLineFilePath;

    public static ArrayList<InvoiceHeader> readFile() {
        return new ArrayList<InvoiceHeader>();
    }

    public static void writeFile(ArrayList<InvoiceHeader> invoiceHeader) {

    }

    public static String getInvoiceHeaderFilePath() {
        return invoiceHeaderFilePath;
    }

    public static void setInvoiceHeaderFilePath(String filePath) {
        invoiceHeaderFilePath = filePath;
    }

    public static String getInvoiceLineFilePath() {
        return invoiceLineFilePath;
    }

    public static void setInvoiceLineFilePath(String filePath) {
        invoiceLineFilePath = filePath;
    }

}
