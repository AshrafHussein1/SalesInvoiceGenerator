package controller;

import model.CurrentLoadedInvoices;
import model.FileOperations;
import view.AppFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveFileListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(AppFrame.isInvoiceDataChanged()) {
            JOptionPane.showMessageDialog(null,
                    "Please save or cancel the current invoice changes before saving the invoices to a file",
                    "Can't save the invoices to file",JOptionPane.WARNING_MESSAGE);
            return;
        }

            String path = "";
            FileOperations.setInvoiceLineFilePath("");
            FileOperations.setInvoiceHeaderFilePath("");

            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
            fc.addChoosableFileFilter(filter);
            fc.setDialogTitle("Choose the invoices csv file for saving");
            fc.setSelectedFile(new File("InvoiceHeader.csv"));
            if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
                path = fc.getSelectedFile().getPath();
                FileOperations.setInvoiceHeaderFilePath(path);
            }

            if (!path.equals("")) {
                path = "";
                JFileChooser fc1 = new JFileChooser(System.getProperty("user.dir"));
                fc1.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter1 = new FileNameExtensionFilter("CSV files", "csv");
                fc1.addChoosableFileFilter(filter1);
                fc1.setDialogTitle("Choose the items csv file");
                fc1.setSelectedFile(new File("InvoiceLine.csv"));
                if (fc1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    path = fc1.getSelectedFile().getPath();
                    FileOperations.setInvoiceLineFilePath(path);
                }
            }

            if (!path.equals("")) {
                FileOperations.writeFile(CurrentLoadedInvoices.getInvoices());
            }
    }
}
