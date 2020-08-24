package persistence;

import exception.NegativeNumberException;
import model.Finances;
import model.Purchase;
import model.PurchaseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class WriterTest {
    private static final String TEST_FILE  = "./data/testAccounts.txt";
    private Writer testWriter;
    private Finances finances;


    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException, NegativeNumberException {
        testWriter = new Writer(new File(TEST_FILE));
        Purchase p1 = new Purchase("milk", 399);
        Purchase p2 = new Purchase("clothing", 2999);
        PurchaseList pl = new PurchaseList();
        pl.addPurchase(p1);
        pl.addPurchase(p2);
        finances = new Finances(pl, 300);
    }

    @Test
    void testWriteFinances() {
        testWriter.write(finances);
        testWriter.close();

        try {
            List<Finances> listF = Reader.readFinances(new File(TEST_FILE));
            Finances attempt = listF.get(0);
            assertEquals(300, attempt.getBalance());
            assertEquals(2, attempt.getPurchaseList().getSize());
            assertEquals("milk", attempt.getPurchaseList().getPurchase(0).getName());
        } catch (IOException | NegativeNumberException e) {
            fail("IOException should not have been thrown");
        }
    }

}
