package controller;

import model.CurrentLoadedInvoices;
import model.FileOperations;
import view.AppFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileListener  implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
            int result;

            //This part is Edited due to review point #1 (the tables should be empty then when you press the load button the data will display on the tables)
            if(CurrentLoadedInvoices.getInvoices() == null || CurrentLoadedInvoices.getInvoices().size() == 0)
                result = JOptionPane.YES_OPTION;
            else
                result = JOptionPane.showConfirmDialog(null,
                    "If you loaded a new invoices file, all the unsaved changes in current invoices will be discarded, do you want to continue ?",
                    "Heads up !",JOptionPane.YES_NO_OPTION);
            //

            if(result == JOptionPane.YES_OPTION){
                String path = "";
                FileOperations.setInvoiceLineFilePath("");
                FileOperations.setInvoiceHeaderFilePath("");

                JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
                fc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
                fc.addChoosableFileFilter(filter);
                fc.setDialogTitle("Choose the invoices csv file");
                if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
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
                    if (fc1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        path = fc1.getSelectedFile().getPath();
                        FileOperations.setInvoiceLineFilePath(path);
                    }
                }

                if (!path.equals("")) {
                    CurrentLoadedInvoices.setInvoices(FileOperations.readFile());
                    AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
                    AppFrame.resetItemsTableAndInvoiceFormToDefault();
                }
            }
    }
}
