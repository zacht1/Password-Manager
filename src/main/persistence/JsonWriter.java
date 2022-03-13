package persistence;

import model.AccountRepository;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a writerAccounts, which writes AccountRepository to given destination file
// Credit: This class is based on the code from the JsonWriter class in JsonSerializationDemo
public class JsonWriter {
    private static final int INDENT = 4;
    PrintWriter writerAccounts;
    PrintWriter writerPassword;
    String destinationFileAccounts;
    String destinationFilePassword;

    // MODIFIES: this
    // EFFECTS: constructs a JsonWriter with the given destination file
    public JsonWriter(String destinationFileAccounts, String destinationFilePassword) {
        this.destinationFileAccounts = destinationFileAccounts;
        this.destinationFilePassword = destinationFilePassword;

    }

    // MODIFIES: this
    // EFFECTS: opens the writerAccounts with destinationFileAccounts, throws FileNotFoundException if
    //          destination file cannot be opened
    public void open() throws FileNotFoundException {
        writerAccounts = new PrintWriter(new File(destinationFileAccounts));
        writerPassword = new PrintWriter(new File(destinationFilePassword));
    }

    // MODIFIES: this
    // EFFECTS: write AccountRepository in Json format to destination file
    public void write(AccountRepository accounts) {
        saveAccountsToFile(accounts.formatJson().toString(INDENT));
        savePasswordToFile(accounts.formatPasswordJson().toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes the writerAccounts
    public void close() {
        writerAccounts.close();
        writerPassword.close();
    }

    // MODIFIES: this
    // EFFECTS: writes the given data to destination file
    public void saveAccountsToFile(String data) {
        writerAccounts.print(data);
    }

    // MODIFIES: this
    // EFFECTS: writes the given data to destination file
    public void savePasswordToFile(String data) {
        writerPassword.print(data);
    }

}
