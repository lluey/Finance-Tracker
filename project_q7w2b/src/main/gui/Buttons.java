package gui;

import exception.NegativeNumberException;
import model.Finances;
import model.Purchase;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

// this class is a panel comprised of buttons
public class Buttons extends JPanel {
    private static final String FINANCES_FILE = "./data/finances.txt";
    private Application app;
    private Finances finances;

    // MODIFIES: this
    // EFFECTS: creates a panel of buttons
    public Buttons(Application app) {
        this.app = app;
        finances = app.getFinances();
        setLayout(new GridLayout(0, 1));
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: button adds a purchase to PurchaseList and then updates the application
    private void purchaseButton() {
        JButton purchaseButton = new JButton("Add purchase");
        purchaseButton.addActionListener(e -> {
            addPurchase();
            refresh();
        });
        add(purchaseButton);
    }

    // EFFECTS: closes current application and makes a new application with updated finances
    private void refresh() {
        app.dispose();
        new Application(finances);
    }

    // MODIFIES: this
    // EFFECTS: creates a button that saves current finances
    private void saveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveFinances());
        add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS: button adds money to balance and updates application
    private void addBalanceButton() {
        JButton addBalanceButton = new JButton("Add balance");
        addBalanceButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Add balance");
            try {
                if (input == null) {
                    return;
                }
                double add = Double.parseDouble(input);
                finances.addMoney(add);
            } catch (NumberFormatException f) {
                JOptionPane.showMessageDialog(null, "Please enter a number");
            } catch (NegativeNumberException f) {
                JOptionPane.showMessageDialog(null, "Enter a positive value.");
            }
            refresh();
        });
        add(addBalanceButton);
    }

    // MODIFIES: this
    // EFFECTS: button deletes selected items, updating the balance and application
    private void deleteButton() {
        JButton deleteBtn = new JButton("Delete selected");
        deleteBtn.addActionListener(e -> {
            int x = 0;
            int size = finances.getPurchaseList().getSize();
            for (int i = 0; i < size; i++) {
                boolean checked = Boolean.parseBoolean(app.tableValueAt(i));
                if (checked) {
                    finances.deletePurchase(x);
                } else {
                    x++;
                }
            }
            refresh();
        });
        add(deleteBtn);
    }

    // EFFECTS: adds the buttons to botPanel
    private void addButtons() {
        purchaseButton();
        addBalanceButton();
        deleteButton();
        saveButton();
    }

    // MODIFIES: this
    // EFFECTS: adds purchase with positive amount to PurchaseList
    public void addPurchase() {
        JTextField nameField = new JTextField();
        JTextField costField = new JTextField();

        Object[] fields = { "Name of purchase", nameField, "Cost", costField };
        int pane = JOptionPane.OK_CANCEL_OPTION;
        int info = JOptionPane.INFORMATION_MESSAGE;
        int option = JOptionPane.showConfirmDialog(null, fields, "Purchase", pane, info);

        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            try {
                Purchase item = new Purchase(name, Double.parseDouble(costField.getText()));
                finances.makePurchase(item);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input.", "Invalid", JOptionPane.ERROR_MESSAGE);
            } catch (NegativeNumberException e) {
                JOptionPane.showMessageDialog(null, "Negative input.", "neg", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // EFFECTS: saves finances to FINANCES_FILE
    private void saveFinances() {
        try {
            Writer writer = new Writer(new File(FINANCES_FILE));
            writer.write(finances);
            writer.close();
            System.out.println("Finances saved to file " + FINANCES_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to " + FINANCES_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
