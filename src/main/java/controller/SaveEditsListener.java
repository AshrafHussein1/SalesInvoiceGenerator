package controller;

import model.CurrentLoadedInvoices;
import model.InvoiceHeader;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveEditsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        InvoiceHeader newInvoiceUpdates = AppFrame.getInvoiceUpdates();
        if(newInvoiceUpdates == null) { return; }
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure that you want to save the invoice changes ?",
                "Heads up !",JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            CurrentLoadedInvoices.updateInvoice(newInvoiceUpdates);
            AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
            AppFrame.resetItemsTableAndInvoiceFormToDefault();
        }
    }
}
