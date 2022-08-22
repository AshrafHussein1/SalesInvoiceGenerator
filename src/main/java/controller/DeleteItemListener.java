package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteItemListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int index , invoiceNum;
        try {
            index = AppFrame.getSelectedItemIndex();
            invoiceNum = AppFrame.getInvoiceNumber();
        } catch(Exception exp) {
            return;
        }
        if(index >= 0)
        {
            CurrentLoadedInvoices.deleteItem(invoiceNum,index);
            AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
            AppFrame.updateInvoiceItemsTableAndInvoiceForm(CurrentLoadedInvoices.getInvoiceByInvoiceNumber(invoiceNum));
        }
    }
}
