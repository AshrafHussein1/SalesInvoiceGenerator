package controller;

import model.CurrentLoadedInvoices;
import view.AppFrame;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ItemsTableListener implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {

        DefaultTableModel tableModel = (DefaultTableModel)e.getSource();
        int row = e.getFirstRow();
        int column = e.getColumn();
        int size = tableModel.getRowCount();
        double price;
        int count;
        int invoiceNum;
        String itemName;

        if (row < 0 || row >= size || column <1 || column >=5) {
            return;
        }

        try {
            price = Double.parseDouble(tableModel.getValueAt(row, 2).toString());
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null,
                    "The following price is not numeric value, please edit the price \r\n" + tableModel.getValueAt(row, 2).toString(),
                    "Can't extract item price", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            count = Integer.parseInt(tableModel.getValueAt(row, 3).toString());
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null,
                    "The following count is not numeric value, please edit the item count \r\n" + tableModel.getValueAt(row, 3).toString(),
                    "Can't extract item count", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            invoiceNum = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null,
                    "The following invoice number is not numeric value, please edit the invoice number \r\n" + tableModel.getValueAt(row, 0).toString(),
                    "Can't extract invoice number", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            itemName = tableModel.getValueAt(row,1).toString();
            if(itemName == null || itemName.equals(""))
            {
                JOptionPane.showMessageDialog(null,
                        "The updated item name field is empty, please write the item name",
                        "Can't extract item name", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(null,
                    "The following item name couldn't be extracted, please edit it \r\n" + tableModel.getValueAt(row, 1).toString(),
                    "Can't extract item name", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CurrentLoadedInvoices.updateItem(invoiceNum,row,itemName,price,count);
        AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        AppFrame.updateInvoiceItemsTableAndInvoiceForm(CurrentLoadedInvoices.getInvoiceByInvoiceNumber(invoiceNum));


    }
}
