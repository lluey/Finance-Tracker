package gui;

import exception.NegativeNumberException;
import model.Finances;
import model.PurchaseList;
import persistence.Reader;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

// This class is where all components are put together

public class Application extends JFrame {
    private static final String FINANCES_FILE = "./data/finances.txt";
    private Table table;
    private Finances finances;

    // MODIFIES: this
    // EFFECTS: assembles gui for application with a table, total and remaining balance, and buttons
    private void runFinances() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Finances");
        setLayout(new BorderLayout());
        table = new Table(finances.getPurchaseList());
        Buttons buttons = new Buttons(this);

        add(new JScrollPane(table), BorderLayout.PAGE_START);
        String colour = "green";
        if (finances.getBalance() <= 0) {
            colour = "red";
        }
        String firstPart = "<html>Total spent: $" + finances.getTotalAmount();
        String secondPart = "<BR> Remaining: $<font color='" + colour + "'>" + finances.getBalance();
        add(new JLabel(firstPart + secondPart), BorderLayout.EAST);
        add(buttons, BorderLayout.PAGE_END);
        pack();
        setVisible(true);
    }

    // EFFECTS: returns finances
    public Finances getFinances() {
        return finances;
    }

    // REQUIRES: i >= 0 and i < size of table
    // EFFECTS: returns value of table at index i in the first column
    public String tableValueAt(int i) {
        return table.getValueAt(i, 0).toString();
    }

    // starts program
    public static void main(String[] args) {
        new Application();
    }

    // EFFECTS: prompts user whether to load saved finances or initialize new finances and does so
    public Application() {
        boolean keepGoing = true;
        // 0 = yes, 1 = no, 2 = cancel
        int load = JOptionPane.showConfirmDialog(null, "Load previous finances?");
        if (load == 0) {
            loadFinances();
        } else if (load == 1) {
            init();
        } else {
            keepGoing = false;
        }
        if (keepGoing) {
            runFinances();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new application with given finances
    public Application(Finances finances) {
        this.finances = finances;
        runFinances();
    }


    // MODIFIES: this
    // EFFECTS: initializes balance and purchase list
    public void init() {
        double value = -1;
        while (value < 0) {
            String input = JOptionPane.showInputDialog("Enter Balance (in dollars).");
            if (input == null) {
                System.exit(0);
            }
            value = Double.parseDouble(input);
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Please enter positive balance.");
            }
        }
        finances = new Finances(new PurchaseList(), value);
    }

    // MODIFIES: this
    // EFFECTS: loads previous file, if it doesn't exist, create new finances
    private void loadFinances() {
        try {
            List<Finances> list = Reader.readFinances(new File(FINANCES_FILE));
            finances = list.get(0);
        } catch (IOException e) {
            init();
        } catch (NegativeNumberException e) {
            System.out.println("Should not have happened");
        }
    }

}


