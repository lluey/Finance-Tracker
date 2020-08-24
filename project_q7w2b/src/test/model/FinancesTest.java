package model;

import exception.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class FinancesTest {
    Purchase p1;
    Purchase p2;
    PurchaseList pl;
    double money;
    Finances fn;

    @BeforeEach
    void runBefore() {
        try {
            p1 = new Purchase("milk", 4.99);
            p2 = new Purchase("toothpaste", 2.75);
            pl = new PurchaseList();
            money = 100;
            fn = new Finances(pl, money);
        } catch (NegativeNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConstructor() {
        assertEquals(0, fn.getPurchaseList().getSize());
        assertEquals(100.0, fn.getBalance());
        assertEquals(0, fn.getTotalAmount());
    }

    @Test
    void makePurchase() {
        fn.makePurchase(p1);
        fn.makePurchase(p2);
        assertEquals(2, fn.getPurchaseList().getSize());
        assertEquals(92.26, fn.getBalance());
        assertEquals(7.74, fn.getTotalAmount());
    }

    @Test
    void addMoneyPositive() {
        try {
            fn.addMoney(200);
            assertEquals(300, fn.getBalance());
        } catch (NegativeNumberException e){
            fail();
        }
    }

    @Test
    void addNegativeMoney() {
        try {
            fn.addMoney(-20);
            fail("Should have thrown exception");
        } catch (NegativeNumberException e) {
            //expected
        }
    }

    @Test
    void deletePurchase() {
        fn.makePurchase(p1);
        fn.makePurchase(p2);
        assertEquals(2, fn.getPurchaseList().getSize());
        assertEquals(92.26, fn.getBalance());
        assertEquals(7.74, fn.getTotalAmount());
        fn.deletePurchase(1);
        assertEquals(1, fn.getPurchaseList().getSize());
        assertEquals(95.01, fn.getBalance());
        assertEquals(4.99, fn.getTotalAmount());
    }

    @Test
    void testSave() {
        fn.getPurchaseList().addPurchase(p1);
        fn.getPurchaseList().addPurchase(p2);

        try {
            File file = new File("./data/testFinances.txt");
            PrintWriter pw = new PrintWriter(file, "UTF-8");
            fn.save(pw);
            pw.close();
            List<Finances> listF = Reader.readFinances(file);
            Finances finances = listF.get(0);
            assertEquals(2, finances.getPurchaseList().getSize());
            assertEquals(100.0, finances.getBalance());

        } catch (IOException | NegativeNumberException e) {
            fail("Unexpected Exception.");
        }
    }
}
