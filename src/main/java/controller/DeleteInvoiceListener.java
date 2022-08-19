package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteInvoiceListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(AppFrame.isInvoiceDataChanged()) {
            JOptionPane.showMessageDialog(null,
                    "Please save or cancel the current invoice changes before changing the invoice",
                    "Can't change the invoice view",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedInvoiceNumber = AppFrame.getSelectedInvoiceNumber();
        if(selectedInvoiceNumber == -1) { return; }
        int result = JOptionPane.showConfirmDialog(null,
                "Are you sure that you want to delete the selected invoice ?",
                "Heads up !",JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION){
            for(int index = 0 ; index < CurrentLoadedInvoices.getInvoices().size() ; index++) {
                if (selectedInvoiceNumber == CurrentLoadedInvoices.getInvoices().get(index).getInvoiceNum())
                {
                    CurrentLoadedInvoices.deleteInvoice(selectedInvoiceNumber);
                    break;
                }
            }
            AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
            AppFrame.resetItemsTableAndInvoiceFormToDefault();
        }
    }
}
