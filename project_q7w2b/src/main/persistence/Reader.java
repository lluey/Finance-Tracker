package persistence;

import exception.NegativeNumberException;
import model.Finances;
import model.Purchase;
import model.PurchaseList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// inspired by TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// A reader that can read finances data from a file
public class Reader {
    public static final String DELIMITER = ",";
    public static final String DELIM2 = ";";
    public int accessed;

    // a dummy constructor
    public Reader() {
        accessed = 0;
    }

    // a dummy getter method
    public int getAccessed() {
        return accessed;
    }

    // EFFECTS: returns a list of finances parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static List<Finances> readFinances(File file) throws IOException, NegativeNumberException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of accounts parsed from list of strings
    // where each string contains data for one finances
    private static List<Finances> parseContent(List<String> fileContent) throws NegativeNumberException {
        List<Finances> accounts = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            accounts.add(parseFinances(lineComponents));
        }

        return accounts;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }



    // REQUIRES: components has size 2 where element 0 represents the
    // PurchaseList, element 1 represents the balance  to be constructed
    // EFFECTS: returns a new finances constructed from components
    private static Finances parseFinances(List<String> components) throws NegativeNumberException  {
        String pl = components.get(0);
        double balance = Double.parseDouble(components.get(1));
        return new Finances(stringToPl(pl), balance);
    }

    // EFFECTS: returns a PurchaseList from the deconstructed string
    private static PurchaseList stringToPl(String str) throws NegativeNumberException {
        PurchaseList pl = new PurchaseList();
        List<String> strs = new ArrayList<>(Arrays.asList(str.split(DELIM2)));
        for (int i = 0; i < strs.size() - 1; i += 2) {
            Purchase p = new Purchase(strs.get(i), Double.parseDouble(strs.get(i + 1)));
            pl.addPurchase(p);
        }
        return pl;
    }
}

