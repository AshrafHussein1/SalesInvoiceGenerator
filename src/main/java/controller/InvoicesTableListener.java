package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;

import javax.swing.*;
import java.awt.event.*;

public class InvoicesTableListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() >= 2) //Act on Double click only
        {
            JTable target = (JTable)e.getSource();
            if(AppFrame.isInvoiceDataChanged())
            {
                JOptionPane.showMessageDialog(null,
                        "Please save or cancel the current invoice changes before changing the invoice",
                        "Can't change the invoice view",JOptionPane.WARNING_MESSAGE);
            } else {
                int invoiceNumber = Integer.parseInt((String)target.getValueAt(target.getSelectedRow(),0));
                AppFrame.updateInvoiceItemsTableAndInvoiceForm(CurrentLoadedInvoices.getInvoiceByInvoiceNumber(invoiceNumber));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
