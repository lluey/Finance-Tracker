package persistence;

import exception.NegativeNumberException;
import model.Finances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testParseFinances() throws NegativeNumberException {
        try {
            List<Finances> listF = Reader.readFinances(new File("./data/testAccounts.txt"));
            Finances finances = listF.get(0);
            assertEquals(2, finances.getPurchaseList().getSize());
            assertEquals(300.0, finances.getBalance());
            assertEquals("clothing", finances.getPurchaseList().getPurchase(1).getName());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testIOException() throws NegativeNumberException {
        try {
            Reader.readFinances(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e){
            // expected
        }
    }

    @Test
    void testConstructor() {
        Reader reader = new Reader();
        assertEquals(0, reader.getAccessed());
    }
}
