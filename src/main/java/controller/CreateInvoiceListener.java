package controller;

import model.CurrentLoadedInvoices;
import model.InvoiceHeader;
import view.AppFrame;
import view.NewInvoiceDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInvoiceListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        NewInvoiceDialog newInvoiceDialog = new NewInvoiceDialog();
        newInvoiceDialog.setVisible(true);
    }
}
