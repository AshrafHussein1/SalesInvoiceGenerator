package testPackage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.FileOperations;
import model.InvoiceHeader;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    ArrayList<InvoiceHeader> invoices;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Test cases
     */
    public void testReadAndWriteFiles() {
        invoices = FileOperations.readFile();
        for (InvoiceHeader invoice : invoices) {
            System.out.println(invoice.getInvoiceNum());
            System.out.println("{");
            System.out.println(invoice.getInvoiceDate() + "," + invoice.getCustomerName());
            for (int itemsIndex = 0; itemsIndex < invoice.getInvoiceLines().size(); itemsIndex++) {
                System.out.println(invoice.getInvoiceLines().get(itemsIndex).getItemName() + "," +
                        invoice.getInvoiceLines().get(itemsIndex).getItemPrice() + "," +
                        invoice.getInvoiceLines().get(itemsIndex).getCount());
            }
            System.out.println("}");
            System.out.println();
        }
        FileOperations.setInvoiceHeaderFilePath("./aaa.csv");
        FileOperations.setInvoiceLineFilePath("./bbb.csv");
        FileOperations.writeFile(invoices);
    }
}
