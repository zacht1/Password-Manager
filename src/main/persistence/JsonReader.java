package persistence;

import model.AccountRepository;
import org.json.JSONObject;

import java.io.IOException;

// represents a reader, which reads AccountRepository from source file
// Credit: This class is based on the code from the JsonReader class in JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads AccountRepository from file and returns it
    public AccountRepository readAccounts() throws IOException {
        return null; //stub
    }

    // EFFECTS: reads the source file as a string a returns it
    public String readString() throws IOException {
        return null;
    }

    // EFFECTS: reads AccountRepository from JSONObject and returns it
    public AccountRepository parseAccountRepository(JSONObject jsonObject) {
        return null; //stub
    }

    // MODIFIES: accounts
    // EFFECTS: parses AccountCards from JSONObject and adds them to accounts
    public void addAccountCards(AccountRepository accounts, JSONObject jsonObject) {
        //stub
    }

    // MODIFIES: accounts
    // EFFECTS: parses an AccountCard from JSONObject and adds it to accounts
    public void addAccountCard(AccountRepository accounts, JSONObject jsonObject) {
        //stub
    }
}
