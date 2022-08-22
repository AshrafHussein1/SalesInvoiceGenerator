package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerNameListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField target = (JTextField) e.getSource();
        String name = target.getText();

        if(name == null || name.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Customer name is changed to empty string, please write customer name",
                    "Can't extract customer name",JOptionPane.ERROR_MESSAGE);
            return;
        }
        int invoiceNum;
        try {
            invoiceNum = AppFrame.getInvoiceNumber();
        } catch (Exception exp) {return;}

        CurrentLoadedInvoices.setCustomerName(invoiceNum,name);
        AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        AppFrame.updateInvoiceItemsTableAndInvoiceForm(CurrentLoadedInvoices.getInvoiceByInvoiceNumber(invoiceNum));
    }
}
