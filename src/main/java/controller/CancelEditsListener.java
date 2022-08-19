package controller;

import view.AppFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelEditsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(AppFrame.isInvoiceDataChanged()) {
            int result = JOptionPane.showConfirmDialog(null,
                    "Are you sure that you want to discard the invoice changes ?",
                    "Heads up !",JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                AppFrame.resetItemsTableAndInvoiceFormToDefault();
            }
            return;
        }
        AppFrame.resetItemsTableAndInvoiceFormToDefault();
    }
}
