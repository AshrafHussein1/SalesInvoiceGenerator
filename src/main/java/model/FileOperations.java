package model;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileOperations {


    private static String invoiceHeaderFilePath="./InvoiceHeader.csv"; //Set default path for InvoiceHeader file
    private static String invoiceLineFilePath="./InvoiceLine.csv"; //Set default path for InvoiceLine file

    public static ArrayList<InvoiceHeader> readFile() {
        //Read data from InvoiceHeader and InvoiceLine and put this data in InvoiceHeader array list
        ArrayList<InvoiceHeader> invoices = new ArrayList<>();
        readFromHeaderFile(invoices);
        readFromLinesFile(invoices);
        return invoices;
    }

    public static void writeFile(ArrayList<InvoiceHeader> invoices) {
        // Write invoices data to InvoiceHeader and InvoiceLine files
        ArrayList<String> headersList = new ArrayList<>(); //this array list represents the file lines
        ArrayList<String> linesList = new ArrayList<>(); //this array list represents the file lines
        if (invoices == null || invoices.size() == 0) { //If there are no invoices to be written, show error message
            int result = JOptionPane.showConfirmDialog(null,
                    "No invoices found to save to the file, Do you want to continue with creating empty files ?",
                    "Didn't find invoices to save", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try {
                    BufferedWriter headerFile = new BufferedWriter(new FileWriter(invoiceHeaderFilePath));
                    headerFile.close();
                    BufferedWriter itemsFile = new BufferedWriter(new FileWriter(invoiceLineFilePath));
                    itemsFile.close();
                } catch (Exception e) {
                    // Do nothing
                }
            }
            return;
        }
        for (InvoiceHeader selectedInvoice : invoices) { // loop on invoices to fill file lines
            headersList.add(selectedInvoice.getInvoiceNum() + "," + selectedInvoice.getInvoiceDate() + "," + selectedInvoice.getCustomerName());
            ArrayList<InvoiceLine> selectedLines = selectedInvoice.getInvoiceLines();
            if (selectedLines != null && selectedLines.size() > 0) {
                for (InvoiceLine selectedLine : selectedLines) { // loop on invoices items to fill file lines
                    linesList.add(selectedInvoice.getInvoiceNum() + "," + selectedLine.getItemName() + "," + selectedLine.getItemPrice() + "," + selectedLine.getCount());
                }
            }
        }

        try{ //write the data to InvoiceHeader using the provided path
            BufferedWriter headerFile = new BufferedWriter(new FileWriter(invoiceHeaderFilePath));
            if (headersList.size() > 0)
            {
                for (String s : headersList) {
                    headerFile.write(s+"\n");
                }
            }
            headerFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Cannot write to the following file, \r\n"+invoiceHeaderFilePath,
                    "Cannot save invoices to the file",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{ //write the data to InvoiceLine using the provided path
            BufferedWriter itemsFile = new BufferedWriter(new FileWriter(invoiceLineFilePath));
            if (linesList.size() > 0)
            {
                for (String s : linesList) {
                    itemsFile.write(s+"\n");
                }
            }
            itemsFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Cannot write to the following file, \r\n"+invoiceLineFilePath,
                    "Cannot save items to the file",JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void setInvoiceHeaderFilePath(String filePath) {
        invoiceHeaderFilePath = filePath;
    }

    public static void setInvoiceLineFilePath(String filePath) {
        invoiceLineFilePath = filePath;
    }

    private static void readFromHeaderFile(ArrayList<InvoiceHeader> invoices) {
        //Read data from InvoiceHeader file and fill Invoices array list with the extracted data
        if(!(invoiceHeaderFilePath.endsWith(".csv")||invoiceHeaderFilePath.endsWith(".CSV"))) {
            //If the file is not with extension .csv, show error message then stop reading
            JOptionPane.showMessageDialog(null,
                    "The file provided is not csv file",
                    "Wrong file format",JOptionPane.ERROR_MESSAGE);
            return;
        }

        BufferedReader fileReader;
        try { //Try to open the file and read it line by line and validate the data before writing them to the arraylist
            fileReader = new BufferedReader(new FileReader(invoiceHeaderFilePath)) ;
            String line;
            while ((line = fileReader.readLine()) != null) {
                int invoiceNo;
                String invoiceDate;
                String name;
                String[] headerFields = line.split(",");
                if (headerFields.length < 3) { //if the row columns less than 3, then the invoice information is missing
                    JOptionPane.showMessageDialog(null,
                            "The following line is less than 3 fields\r\n" + line,
                            "Can't extract invoice data", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                try { //Validate the Invoice number is valid integer
                    invoiceNo = Integer.parseInt(headerFields[0]);
                    if (invoiceNo < 1) {
                        JOptionPane.showMessageDialog(null,
                                "The following invoice number is less than 1\r\n" + line,
                                "Can't extract invoice number", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "The following invoice number is not integer\r\n" + line,
                            "Can't extract invoice number", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                try { //Validate the invoice date is DD-MM-YYYY
                    invoiceDate = headerFields[1];
                    String[] dateFields = invoiceDate.split("-");
                    int day = Integer.parseInt(dateFields[0]);
                    int month = Integer.parseInt(dateFields[1]);
                    Integer.parseInt(dateFields[2]);

                    if (month < 1 || month > 12) {
                        throw new Exception();
                    }
                    if (day < 1 || day > 31) {
                        throw new Exception();
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "The application can't extract the following date\r\n" + line,
                            "Can't extract invoice date", JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                name = headerFields[2];
                invoices.add(new InvoiceHeader(invoiceNo, invoiceDate, name));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "The file provided is not found\r\n"+invoiceHeaderFilePath,
                    "File not found",JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "The application can't extract data from the following file\r\n"+invoiceHeaderFilePath,
                    "Can't extract data from file",JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void readFromLinesFile(ArrayList<InvoiceHeader> invoices) {
        //Read data from InvoiceLine file and fill Invoices array list with the extracted data
        if(!(invoiceLineFilePath.endsWith(".csv")||invoiceLineFilePath.endsWith(".CSV")))
        {
            //If the file is not with extension .csv, show error message then stop reading
            JOptionPane.showMessageDialog(null,
                    "The file provided is not csv file",
                    "Wrong file format",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try { //Try to open the file and read it line by line to extract invoice items data
            BufferedReader fileReader = new BufferedReader(new FileReader(invoiceLineFilePath)) ;
            String line;
            while ((line = fileReader.readLine()) != null) {
                int invoiceNo;
                String name;
                double price;
                int count;
                String[] headerFields = line.split(",");
                if(headerFields.length < 4)
                { //Show error message if the number of columns in one line is less than 4
                    JOptionPane.showMessageDialog(null,
                            "The following line is less than 4 fields\r\n"+line,
                            "Can't extract invoice data",JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                try { //Validate the invoice number is an integer and equals or more than 1
                    invoiceNo = Integer.parseInt(headerFields[0]);
                    if (invoiceNo<1)
                    {
                        JOptionPane.showMessageDialog(null,
                                "The following invoice number is less than 1\r\n"+line,
                                "Can't extract invoice number",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,
                            "The following invoice number is not integer\r\n"+line,
                            "Can't extract invoice number",JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                try {//Validate the items count is an integer and equals or more than 1
                    count = Integer.parseInt(headerFields[3]);
                    if (count<0)
                    {
                        JOptionPane.showMessageDialog(null,
                                "The following items count is less than 1\r\n"+line,
                                "Can't extract items count",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,
                            "The following items count is not integer\r\n"+line,
                            "Can't extract items count",JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                try{ //Validate the price is double number
                    price = Double.parseDouble(headerFields[2]);
                    if (price<0)
                    {
                        JOptionPane.showMessageDialog(null,
                                "The following item price is less than or equal to 0\r\n"+line,
                                "Can't extract item price",JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null,
                            "The following item price is not numeric value\r\n"+line,
                            "Can't extract item price",JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                name = headerFields[1];
                if (invoices == null || invoices.size() == 0)
                { //show error message if you didn't find any invoices extracted from InvoiceHeader file
                    JOptionPane.showMessageDialog(null,
                            "the invoices list is empty, can't relate the items to any invoices",
                            "Invoices list is empty",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                InvoiceLine newInvoiceLine = new InvoiceLine(name,price,count);
                for (InvoiceHeader invoice : invoices) {
                    if (invoice.getInvoiceNum() == invoiceNo) {
                        invoice.addInvoiceLine(newInvoiceLine); //Add the new item to the corresponding invoice
                        break;
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "The file provided is not found\r\n"+invoiceLineFilePath,
                    "File not found",JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "The application can't extract data from the following file\r\n"+invoiceLineFilePath,
                    "Can't extract data from file",JOptionPane.ERROR_MESSAGE);
        }
    }

}
