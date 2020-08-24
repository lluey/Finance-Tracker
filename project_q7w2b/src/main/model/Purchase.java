package model;

import exception.NegativeNumberException;

// This class represents individual items purchased with a name and how much it cost in cents
public class Purchase {
    public String name;
    public double cost;

    // REQUIRES: cost >= 0
    // EFFECTS: constructs a purchase with its name and cost in cents
    public Purchase(String name, double cost) throws NegativeNumberException {
        if (cost < 0) {
            throw new NegativeNumberException();
        }
        this.name = name;
        this.cost = cost;
    }

    // EFFECTS: returns cost of purchase
    public double getCost() {
        return cost;
    }

    // EFFECTS: returns the name of item purchased
    public String getName() {
        return name;
    }
}
