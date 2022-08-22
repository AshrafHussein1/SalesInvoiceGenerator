package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InvoiceDateListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField target = (JTextField) e.getSource();
        String date = target.getText();

        try { //Validate the invoice date is DD-MM-YYYY
            String[] dateFields = date.split("-");
            int day = Integer.parseInt(dateFields[0]);
            int month = Integer.parseInt(dateFields[1]);
            Integer.parseInt(dateFields[2]);

            if (month < 1 || month > 12) {
                throw new Exception();
            }
            if (day < 1 || day > 31) {
                throw new Exception();
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null,
                    "The application can't extract the following date, please use DD-MM-YYYY format\r\n" + date,
                    "Can't extract invoice date", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int invoiceNum;
        try {
            invoiceNum = AppFrame.getInvoiceNumber();
        } catch (Exception exp) {return;}

        CurrentLoadedInvoices.setInvoiceDate(invoiceNum,date);
        AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        AppFrame.updateInvoiceItemsTableAndInvoiceForm(CurrentLoadedInvoices.getInvoiceByInvoiceNumber(invoiceNum));

    }
}
