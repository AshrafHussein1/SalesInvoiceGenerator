package view;

import controller.*;
import model.InvoiceHeader;
import model.InvoiceLine;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AppFrame extends JFrame {

    private static final String[] invoicesTableHeader = new String[]{"No.","Date","Customer","Total"};
    private static final String[] itemsTableHeader = new String[]{"No.","Item Name","Item Price","Count","Item Total"};
    private static DefaultTableModel itemsTableModel;
    private static DefaultTableModel invoicesTableModel;
    private static JTable invoicesTable;
    private static JTable invoiceItemsTable;
    private static JTextField invoiceNumber;
    private static JTextField invoiceDate;
    private static JTextField customerName;
    private static JTextField invoiceTotal;

    static String initialCustomerName;
    static String initialInvoiceDate;
    static String[][] initialItemsTableData;

    public AppFrame() {
        // The main application frame configurations
        this.setTitle("Sales Invoice Generator");
        this.setSize(800,450);
        this.setLocation(200,100);
        this.setLayout(new GridLayout(1,2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Menu bar configurations
        JMenu menu = new JMenu("File");
        JMenuItem saveFile = new JMenuItem("Save File");
        SaveFileListener saveFileListener = new SaveFileListener();
        saveFile.addActionListener(saveFileListener);
        menu.add(saveFile);
        JMenuItem loadFile = new JMenuItem("Load File");
        LoadFileListener loadFileListener = new LoadFileListener();
        loadFile.addActionListener(loadFileListener);
        menu.add(loadFile);
        JMenuBar menuBar = new JMenuBar();
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

        /* This part is commented due to review point #1 (the tables should be empty then when you press the load button the data will display on the tables)
        if(CurrentLoadedInvoices.getInvoices() == null || CurrentLoadedInvoices.getInvoices().size() == 0)
            invoicesTableModel.setColumnIdentifiers(invoicesTableHeader);
        else updateInvoicesTable(CurrentLoadedInvoices.getInvoices());*/
        invoicesTableModel.setColumnIdentifiers(invoicesTableHeader);

        invoicesTable = new JTable(invoicesTableModel);
        invoicesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invoicesTable.setShowGrid(true);
        invoicesTable.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        InvoicesTableListener invoicesTableListener = new InvoicesTableListener();
        invoicesTable.addMouseListener(invoicesTableListener);
        JPanel invoicesPanel = new JPanel();
        invoicesPanel.setBorder(BorderFactory.createTitledBorder("Invoice Table"));
        JScrollPane sp2 = new JScrollPane(invoicesTable);
        sp2.setPreferredSize(new Dimension(380, 310));
        invoicesPanel.add(sp2);

        // Create new invoice button configurations
        JButton createNewInvoice = new JButton("Create New Invoice");
        CreateInvoiceListener createInvoiceListener = new CreateInvoiceListener();
        createNewInvoice.addActionListener(createInvoiceListener);

        // Delete invoice button configurations
        JButton deleteInvoice = new JButton("Delete Invoice");
        DeleteInvoiceListener deleteInvoiceListener = new DeleteInvoiceListener();
        deleteInvoice.addActionListener(deleteInvoiceListener);

        // Left side panel configurations
        // (Invoices table - Create New Invoice button - Delete Invoice button)
        JPanel leftSidePanel = new JPanel();
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
        InvoiceDateListener invoiceDateListener = new InvoiceDateListener();
        invoiceDate.addActionListener(invoiceDateListener);

        // Customer name text field configurations
        customerName = new JTextField (15);
        CustomerNameListener customerNameListener = new CustomerNameListener();
        customerName.addActionListener(customerNameListener);

        // Invoice total text field configurations
        invoiceTotal = new JTextField (15);
        invoiceTotal.setEditable(false);
        invoiceTotal.setBackground(null);

        // Invoice form configurations
        JPanel invoiceFormPanel = new JPanel();
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
                //all cells in first and last columns are false
                return column != 0 && column != 4;
            }
        };
        itemsTableModel.setColumnIdentifiers(itemsTableHeader);
        invoiceItemsTable = new JTable(itemsTableModel);
        invoiceItemsTable.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        invoiceItemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ItemsTableListener itemsTableListener = new ItemsTableListener();
        itemsTableModel.addTableModelListener(itemsTableListener);
        JPanel invoiceItemsPanel = new JPanel();
        invoiceItemsPanel.setLayout(new FlowLayout());
        invoiceItemsPanel.setBorder(BorderFactory.createTitledBorder("Invoice Items"));
        JScrollPane sp1 = new JScrollPane(invoiceItemsTable);
        sp1.setPreferredSize(new Dimension(380, 200));
        invoiceItemsPanel.add(sp1);

        // Save button configurations
        JButton createItem = new JButton("Create Item");
        CreateItemListener createItemListener = new CreateItemListener();
        createItem.addActionListener(createItemListener);

        // Cancel button configurations
        JButton deleteItem = new JButton("Delete Item");
        DeleteItemListener deleteItemListener = new DeleteItemListener();
        deleteItem.addActionListener(deleteItemListener);

        // Left side panel configurations (Invoice form - Invoice items table - Save button - Cancel button)
        JPanel rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new FlowLayout());
        rightSidePanel.add(invoiceFormPanel);
        rightSidePanel.add(invoiceItemsPanel);
        rightSidePanel.add(createItem);
        rightSidePanel.add(deleteItem);
        this.add(rightSidePanel);

        this.setVisible(true);
    }

    public static void updateInvoicesTable(ArrayList<InvoiceHeader> invoices) {
        // Update the invoices table with the array list of invoices provided
        if(invoices != null && invoices.size()>0) {
            String[][] tableData = new String[invoices.size()][4];
            for(int index = 0 ; index < invoices.size() ; index++)
            {
                tableData[index][0] = Integer.toString(invoices.get(index).getInvoiceNum());
                tableData[index][1] = invoices.get(index).getInvoiceDate();
                tableData[index][2] = invoices.get(index).getCustomerName();
                double total = 0;
                if (invoices.get(index).getInvoiceLines() == null || invoices.get(index).getInvoiceLines().size() == 0) {
                    //Do nothing
                }
                else {
                    for (InvoiceLine item : invoices.get(index).getInvoiceLines()) {
                        total = total + (item.getCount() * item.getItemPrice());
                    }
                }
                tableData[index][3] = Double.toString(total);
            }
            invoicesTableModel.setDataVector(tableData,invoicesTableHeader);
            invoicesTableModel.fireTableDataChanged();
        } else{
            invoicesTableModel.setNumRows(0);
            invoicesTableModel.fireTableDataChanged();
        }
    }

    public static void updateInvoiceItemsTableAndInvoiceForm(InvoiceHeader invoice) {
        if (invoice == null) //if no invoice passed, don't do anything
        {
            return;
        }
        // Update the invoice form and the items table with the provided invoice info
        invoiceNumber.setText(Integer.toString(invoice.getInvoiceNum()));
        invoiceDate.setText(invoice.getInvoiceDate());
        customerName.setText(invoice.getCustomerName());

        initialCustomerName = invoice.getCustomerName();
        initialInvoiceDate = invoice.getInvoiceDate();

        ArrayList<InvoiceLine> items = invoice.getInvoiceLines();
        double total = 0;
        if (items == null || items.size() == 0) {
            itemsTableModel.setNumRows(0);
            itemsTableModel.fireTableDataChanged();
        }
        else {
            String[][] itemsData= new String[items.size()][5];
            for (int index = 0 ; index< items.size() ; index++) {
                total = total + (items.get(index).getCount() * items.get(index).getItemPrice());
                itemsData[index][0] = Integer.toString(invoice.getInvoiceNum());
                itemsData[index][1] = items.get(index).getItemName();
                itemsData[index][2] = Double.toString(items.get(index).getItemPrice());
                itemsData[index][3] = Integer.toString(items.get(index).getCount());
                itemsData[index][4] = Double.toString(items.get(index).getCount() * items.get(index).getItemPrice());
            }
            itemsTableModel.setDataVector(itemsData,itemsTableHeader);
            initialItemsTableData = itemsData;
            itemsTableModel.fireTableDataChanged();
        }
        invoiceTotal.setText(Double.toString(total));
    }

    public static void resetItemsTableAndInvoiceFormToDefault() {
        // After saving or cancelling invoice updates, set the items table and invoice form empty again to wait for opening a new invoice
        invoiceNumber.setText("");
        invoiceDate.setText("");
        customerName.setText("");
        invoiceTotal.setText("");
        initialInvoiceDate = "";
        initialCustomerName = "";
        initialItemsTableData = null;
        itemsTableModel.setNumRows(0);
        itemsTableModel.fireTableDataChanged();
    }

    public static int getSelectedInvoiceNumber() {
        if (invoicesTable.getSelectedRow() == -1)
        {
            return -1;
        }
        return Integer.parseInt(invoicesTable.getValueAt(invoicesTable.getSelectedRow(),0).toString());
    }

    public static int getInvoiceNumber() {
        return Integer.parseInt(invoiceNumber.getText());
    }

    public static int getSelectedItemIndex() {
        return invoiceItemsTable.getSelectedRow();
    }
}
