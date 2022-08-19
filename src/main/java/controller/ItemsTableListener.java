package controller;

import view.AppFrame;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ItemsTableListener implements TableModelListener {

    @Override
    public void tableChanged(TableModelEvent e) {
        DefaultTableModel tableModel = (DefaultTableModel)e.getSource();
        int row = e.getFirstRow();
        int size = tableModel.getRowCount();

        if (row < 0 || row >= size) {
            return;
        }

        if (tableModel.getValueAt(row,1) != null &&
                tableModel.getValueAt(row,2) != null &&
                tableModel.getValueAt(row,3) != null &&
                !tableModel.getValueAt(row,1).toString().equals("") &&
                !tableModel.getValueAt(row,2).toString().equals("") &&
                !tableModel.getValueAt(row,3).toString().equals("")) {
            tableModel.removeTableModelListener(this);
            tableModel.setValueAt(AppFrame.getInvoiceNumber(),row,0);
            try {
                double total = Double.parseDouble(tableModel.getValueAt(row, 2).toString())
                        * Integer.parseInt(tableModel.getValueAt(row, 3).toString());
                tableModel.setValueAt(Double.toString(total), row, 4);
            } catch(Exception ignored) {}
            try {
                double invoiceTotal=0;
                for(int index = 0 ; index < size ; index++)
                {
                    try {
                        invoiceTotal = invoiceTotal + Double.parseDouble(tableModel.getValueAt(index, 4).toString());
                    } catch(Exception ignored) {}
                }
                AppFrame.setInvoiceTotal(invoiceTotal);
            } catch(Exception ignored) {}
            if (row == size - 1) {
                tableModel.setNumRows(size+1);
            }
            tableModel.fireTableDataChanged();
            tableModel.addTableModelListener(this);
        }
        else {
            tableModel.removeTableModelListener(this);
            tableModel.setValueAt("", row, 4);
            tableModel.setValueAt("", row, 0);
            tableModel.fireTableDataChanged();
            tableModel.addTableModelListener(this);
        }
    }
}
