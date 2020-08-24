package model;

import exception.NegativeNumberException;
import persistence.Saveable;
import persistence.Reader;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

// represents a file with a PurchaseList and balance
public class Finances implements Saveable {

    private PurchaseList pl;
    private double balance;
    private double totalAmount;

    // EFFECTS: constructs with a PurchaseList and Balance
    public Finances(PurchaseList pl, double initial) {
        this.pl = pl;
        this.balance = initial;
        totalAmount = initializeTotal();
    }

    // EFFECTS: returns double of sum of all cost of items in pl
    private double initializeTotal() {
        double total = 0;
        for (int i = 0; i < pl.getSize(); i++) {
            total += pl.getPurchase(i).getCost();
        }
        return total;
    }

    // EFFECTS: returns the PurchaseList
    public PurchaseList getPurchaseList() {
        return pl;
    }

    // MODIFIES: this
    // EFFECTS: subtracts cost of purchase from the balance and updates list
    public void makePurchase(Purchase item) {
        pl.addPurchase(item);
        balance -= item.getCost();
        totalAmount += item.getCost();
    }

    // MODIFIES: this
    // EFFECTS: adds money to balance
    public void addMoney(double amount) throws NegativeNumberException {
        if (amount < 0) {
            throw new NegativeNumberException();
        }
        balance +=  amount;
    }

    // MODIFIES: this
    // EFFECTS: deletes purchase from list and refunds money and updates totalAmount
    public void deletePurchase(int i) {
        double amount = pl.getPurchase(i).getCost();
        balance += amount;
        pl.remove(i);
        totalAmount -= amount;
    }

    // EFFECTS: returns rounded total amount in dollars
    public double getTotalAmount() {
        return round(totalAmount);
    }

    // EFFECTS: returns balance in dollars
    public double getBalance() {
        return round(balance);
    }

    // taken from: https://www.baeldung.com/java-round-decimal-number
    // EFFECTS: returns value with 2 decimal placing
    public static double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // MODIFIES: printWriter
    // EFFECTS: writes the Finances to printWriter
    @Override
    public void save(PrintWriter printWriter) {
        for (int i = 0; i < pl.getSize(); i++) {
            if (i != 0) {
                printWriter.print(Reader.DELIM2);
            }
            printWriter.print(pl.getPurchase(i).getName());
            printWriter.print(Reader.DELIM2);
            printWriter.print(pl.getPurchase(i).getCost());
        }
        printWriter.print(Reader.DELIMITER);
        printWriter.println(getBalance());
    }
}
