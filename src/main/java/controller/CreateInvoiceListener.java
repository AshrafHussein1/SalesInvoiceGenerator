package controller;

import model.CurrentLoadedInvoices;
import model.InvoiceHeader;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateInvoiceListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(AppFrame.isInvoiceDataChanged()) {
            JOptionPane.showMessageDialog(null,
                    "Please save or cancel the current invoice changes before changing the invoice",
                    "Can't change the invoice view",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int invoiceNum = 1;
        while(true)
        {
            boolean foundNewNumber = true;
            for(InvoiceHeader invoice : CurrentLoadedInvoices.getInvoices())
            {
                if (invoiceNum == invoice.getInvoiceNum())
                {
                    foundNewNumber = false;
                    try {
                        invoiceNum++;
                    } catch (Exception exp) {
                        return;
                    }
                    break;
                }
            }
            if(foundNewNumber)
            {
                break;
            }
        }
        AppFrame.startNewInvoice(invoiceNum);
    }
}
