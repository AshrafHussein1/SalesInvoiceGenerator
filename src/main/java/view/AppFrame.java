package view;

import model.CurrentLoadedInvoices;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.EventObject;

public class AppFrame extends JFrame {

    private static String[] invoicesTableHeader = new String[]{"No.","Date","Customer","Total"};
    private static String[] itemsTableHeader = new String[]{"No.","Item Name","Item Price","Count","Item Total"};
    private static DefaultTableModel itemsTableModel;
    private static DefaultTableModel invoicesTableModel;
    private static JTable invoicesTable;
    private static JTable invoiceItemsTable;
    private static JTextField invoiceNumber;
    private static JTextField invoiceDate;
    private static JTextField customerName;
    private static JTextField invoiceTotal;
    private JPanel leftSidePanel;
    private JPanel rightSidePanel;
    private JPanel invoicesPanel;
    private JPanel invoiceItemsPanel;
    private JPanel invoiceFormPanel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem saveFile;
    private JMenuItem loadFile;
    private JButton createNewInvoice;
    private JButton deleteInvoice;
    private JButton save;
    private JButton cancel;

    public AppFrame() {
        // The main application frame configurations
        this.setTitle("Sales Invoice Generator");
        this.setSize(800,450);
        this.setLocation(200,100);
        this.setLayout(new GridLayout(1,2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Menu bar configurations
        menu = new JMenu("File");
        saveFile = new JMenuItem("Save File");
        menu.add(saveFile);
        loadFile = new JMenuItem("Load File");
        menu.add(loadFile);
        menuBar = new JMenuBar();
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        // Invoices table configurations
        invoicesTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        if(CurrentLoadedInvoices.getInvoices() == null || CurrentLoadedInvoices.getInvoices().size() == 0)
            invoicesTableModel.setColumnIdentifiers(invoicesTableHeader);
        else updateInvoicesTable(CurrentLoadedInvoices.getInvoices());
        invoicesTable = new JTable(invoicesTableModel);
        invoicesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invoicesTable.setShowGrid(true);
        invoicesTable.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        invoicesPanel = new JPanel();
        invoicesPanel.setBorder(BorderFactory.createTitledBorder("Invoice Table"));
        JScrollPane sp2 = new JScrollPane(invoicesTable);
        sp2.setPreferredSize(new Dimension(380, 310));
        invoicesPanel.add(sp2);

        // Create new invoice button configurations
        createNewInvoice = new JButton("Create New Invoice");

        // Delete invoice button configurations
        deleteInvoice = new JButton("Delete Invoice");

        // Left side panel configurations
        // (Invoices table - Create New Invoice button - Delete Invoice button)
        leftSidePanel = new JPanel();
        leftSidePanel.add(invoicesPanel);
        leftSidePanel.add(createNewInvoice);
        leftSidePanel.add(deleteInvoice);
        this.add(leftSidePanel);

        // Invoice number text field configurations
        invoiceNumber = new JTextField(15);
        invoiceNumber.setBackground(null);
        invoiceNumber.setEditable(false);

        // Invoice date text field configurations
        invoiceDate = new JTextField  (15);

        // Customer name text field configurations
        customerName = new JTextField (15);

        // Invoice total text field configurations
        invoiceTotal = new JTextField (15);
        invoiceTotal.setEditable(false);
        invoiceTotal.setBackground(null);

        // Invoice form configurations
        invoiceFormPanel = new JPanel();
        invoiceFormPanel.setLayout(new GridLayout(4,2));
        invoiceFormPanel.add(new JLabel("Invoice Number"));
        invoiceFormPanel.add(invoiceNumber);
        invoiceFormPanel.add(new JLabel("Invoice Date"));
        invoiceFormPanel.add(invoiceDate);
        invoiceFormPanel.add(new JLabel("Customer Name"));
        invoiceFormPanel.add(customerName);
        invoiceFormPanel.add(new JLabel("Invoice Total"));
        invoiceFormPanel.add(invoiceTotal);


        // Invoice items table configurations
        itemsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                if(column == 0 || column == 4)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
        };
        itemsTableModel.setColumnIdentifiers(itemsTableHeader);
        invoiceItemsTable = new JTable(itemsTableModel);
        invoiceItemsTable.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        invoiceItemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invoiceItemsPanel = new JPanel();
        invoiceItemsPanel.setLayout(new FlowLayout());
        invoiceItemsPanel.setBorder(BorderFactory.createTitledBorder("Invoice Items"));
        JScrollPane sp1 = new JScrollPane(invoiceItemsTable);
        sp1.setPreferredSize(new Dimension(380, 200));
        invoiceItemsPanel.add(sp1);

        // Save button configurations
        save = new JButton("Save");

        // Cancel button configurations
        cancel = new JButton("Cancel");

        // Left side panel configurations (Invoice form - Invoice items table - Save button - Cancel button)
        rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new FlowLayout());
        rightSidePanel.add(invoiceFormPanel);
        rightSidePanel.add(invoiceItemsPanel);
        rightSidePanel.add(save);
        rightSidePanel.add(cancel);
        this.add(rightSidePanel);

        this.setVisible(true);
    }

    public static void updateInvoicesTable(ArrayList<InvoiceHeader> invoices) {
        if(invoices != null && invoices.size()>0)
        {
            String[][] tableData = new String[invoices.size()][4];
            for(int index = 0 ; index < invoices.size() ; index++)
            {
                tableData[index][0] = Integer.toString(invoices.get(index).getInvoiceNum());
                tableData[index][1] = invoices.get(index).getInvoiceDate();
                tableData[index][2] = invoices.get(index).getCustomerName();
                double total = 0;
                for(InvoiceLine item : invoices.get(index).getInvoiceLines())
                {
                    total = total + (item.getCount()*item.getItemPrice());
                }
                tableData[index][3] = Double.toString(total);
            }
            invoicesTableModel.setDataVector(tableData,invoicesTableHeader);
        }
    }

    public static void updateInvoiceItemsTableAndInvoiceForm(InvoiceHeader invoice) {

    }

    public static InvoiceHeader getInvoiceUpdates() {
        return new InvoiceHeader();
    }


}
