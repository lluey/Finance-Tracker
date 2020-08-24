package model;

import java.util.ArrayList;

// This class is a list of purchases
public class PurchaseList {

    public ArrayList<Purchase> list;

    // EFFECTS: constructs new list of purchases with the total amount spent at zero cents, and size of list at zero
    public PurchaseList() {
        list = new ArrayList<>();
    }

    // MODIFIES: this and total amount
    // EFFECTS: adds purchase to this and adds its cost to total amount spent
    public void addPurchase(Purchase item) {
        list.add(item);
    }

    // EFFECTS: returns the amount of items in list
    public int getSize() {
        return list.size();
    }

    // EFFECTS: returns the purchase at i
    public Purchase getPurchase(int i) {
        return list.get(i);
    }

    // MODIFIES: this
    // EFFECTS: removes the purchase at i and reverts total amount
    public void remove(int i) {
        list.remove(i);
    }
}
