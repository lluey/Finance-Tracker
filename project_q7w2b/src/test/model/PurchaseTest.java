package model;

import exception.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for Purchase class
public class PurchaseTest {

    @Test
    void testConstructor() {
        try {
            Purchase p1 = new Purchase("milk", 499);
            assertEquals(p1.getCost(), 499);
            assertEquals(p1.getName(), "milk");
        } catch (NegativeNumberException e) {
            fail();
        }
    }

    @Test
    void negativePurchase() {
        try {
            Purchase p = new Purchase("milk", -79);
            fail();
        } catch (NegativeNumberException e) {
            // expected
        }
    }
}
