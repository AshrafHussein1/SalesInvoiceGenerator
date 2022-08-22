package view;

import model.CurrentLoadedInvoices;
import model.InvoiceHeader;

import javax.swing.*;
import java.awt.event.*;

public class NewInvoiceDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField CustomerName;
    private JTextField InvoiceDate;
    private JTextField InvoiceNumber;

    public NewInvoiceDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(400,400);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        int number;
        String date;
        String name;
        try{
            number = Integer.parseInt(InvoiceNumber.getText());
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(this,
                    "The application can't extract the invoice number, please make sure it's an integer",
                    "Can't extract invoice number", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try { //Validate the invoice date is DD-MM-YYYY
            date = InvoiceDate.getText();
            String[] dateFields = date.split("-");
            int day = Integer.parseInt(dateFields[0]);
            int month = Integer.parseInt(dateFields[1]);
            Integer.parseInt(dateFields[2]);

            if (month < 1 || month > 12) {
                throw new Exception();
            }
            if (day < 1 || day > 31) {
                throw new Exception();
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(this,
                    "The application can't extract the date, please use DD-MM-YYYY format",
                    "Can't extract invoice date", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            name = CustomerName.getText();
            if(name == null || name.equals(""))
            {
                throw new Exception();
            }
        } catch (Exception exp) {
            JOptionPane.showMessageDialog(this,
                    "The application can't extract the customer name, please make sure you didn't leave it empty",
                    "Can't extract customer name", JOptionPane.ERROR_MESSAGE);
            return;
        }
        InvoiceHeader newInvoice = new InvoiceHeader(number,date,name);
        CurrentLoadedInvoices.updateInvoice(newInvoice);
        AppFrame.updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        AppFrame.resetItemsTableAndInvoiceFormToDefault();
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
