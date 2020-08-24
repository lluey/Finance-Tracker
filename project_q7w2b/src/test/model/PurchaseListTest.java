package model;

import exception.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for PurchaseList class
class PurchaseListTest {
    Purchase p1;
    Purchase p2;
    PurchaseList pl;
    double money;

    @BeforeEach
    void runBefore() {
        try {
            p1 = new Purchase("milk", 4.99);
            p2 = new Purchase("toothpaste", 2.75);
            pl = new PurchaseList();
            money = 100;
        } catch (NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConstructor() {
        assertEquals(pl.getSize(), 0);
    }

    @Test
    void testAddPurchase() {
        pl.addPurchase(p1);
        pl.addPurchase(p2);

        assertEquals(pl.getSize(), 2);
    }

    @Test
    void testRemove() {
        pl.addPurchase(p1);
        pl.addPurchase(p2);
        pl.remove(1);

        assertEquals(pl.getSize(), 1);
    }

    @Test
    void testGetPurchase() {
        pl.addPurchase(p1);
        pl.addPurchase(p2);
        assertEquals("milk", pl.getPurchase(0).getName());
    }
}