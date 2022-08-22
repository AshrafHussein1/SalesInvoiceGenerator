package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int invoiceNum;
        try {
            invoiceNum = AppFrame.getInvoiceNumber();
        } catch(Exception exp) {
            return;
        }
        CurrentLoadedInvoices.addNewItem(invoiceNum);
        AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        AppFrame.updateInvoiceItemsTableAndInvoiceForm(CurrentLoadedInvoices.getInvoiceByInvoiceNumber(invoiceNum));
    }
}
