package gui;

import model.Purchase;
import model.PurchaseList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

// This class is a table where PurchaseList is displayed

public class Table extends JTable {
    private PurchaseList pl;

    // inspired by: camposha.info/java-jtable-render-checkbox-column/
    //MODIFIES: this
    //EFFECTS: creates a table with a PurchaseList
    public Table(PurchaseList pl) {
        this.pl = pl;
        DefaultTableModel model = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return Boolean.class;
                } else {
                    return String.class;
                }
            }
        };
        setModel(model);
        model.addColumn("Delete?");
        model.addColumn("name");
        model.addColumn("price");
        for (int i = 0; i < pl.getSize(); i++) {
            model.addRow(new Object[3]);
            model.setValueAt(false, i, 0);
            Purchase p = pl.getPurchase(i);
            model.setValueAt(p.getName(), i, 1);
            model.setValueAt("$ " + p.getCost(), i, 2);
        }
        tableSpecification();
    }

    // MODIFIES: this
    // EFFECTS: changes size of table depending on number of items
    private void tableSpecification() {
        setRowHeight(20);
        int height = pl.getSize() * getRowHeight();
        if (height < 1000) {
            setPreferredScrollableViewportSize(new Dimension(500, height));
        } else {
            setPreferredScrollableViewportSize(new Dimension(500, 1000));
        }
        setFillsViewportHeight(true);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

}


