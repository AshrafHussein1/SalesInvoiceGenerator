package controller;

import model.CurrentLoadedInvoices;
import model.InvoiceHeader;
import view.AppFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInvoiceListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String todayDate = dateFormat.format(date);
        InvoiceHeader newInvoice = new InvoiceHeader(invoiceNum,todayDate,"Customer"+invoiceNum);
        CurrentLoadedInvoices.updateInvoice(newInvoice);
        AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        AppFrame.resetItemsTableAndInvoiceFormToDefault();
    }
}
