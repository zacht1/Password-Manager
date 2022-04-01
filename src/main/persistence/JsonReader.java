package persistence;

import model.AccountCard;
import model.AccountRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// represents a reader, which reads AccountRepository from sourceAccounts file
// Credit: This class is based on the code from the JsonReader class in JsonSerializationDemo
public class JsonReader {
    private final String sourceAccounts;
    private final String sourcePassword;

    // EFFECTS: constructs a reader to read from the sourceAccounts file
    public JsonReader(String sourceAccounts, String sourcePassword) {
        this.sourceAccounts = sourceAccounts;
        this.sourcePassword = sourcePassword;
    }

    // EFFECTS: reads AccountRepository from file and returns it,
    //          throws IOException if cannot read from sourceAccounts file
    public AccountRepository readAccounts() throws IOException {
        String data = readString(sourceAccounts);
        JSONObject jsonObject = new JSONObject(data);
        return parseAccountRepository(jsonObject);
    }

    // EFFECTS: reads AccountRepository master password from file and returns it,
    //          throws IOException if cannot read from sourcesPassword file
    public String readMasterPassword() throws IOException {
        String data = readString(sourcePassword);
        JSONObject jsonObject = new JSONObject(data);
        if (jsonObject.has("masterPassword")) {
            return jsonObject.getString("masterPassword");
        } else {
            return null;
        }
    }

    // EFFECTS: reads the sourceAccounts file as a string a returns it, throws IOException if
    //          cannot read from sourceAccounts file
    public String readString(String source) throws IOException {
        StringBuilder dataBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(dataBuilder::append);
        }

        return dataBuilder.toString();
    }

    // EFFECTS: reads AccountRepository from JSONObject and returns it
    public AccountRepository parseAccountRepository(JSONObject jsonObject) {
        AccountRepository accounts = new AccountRepository(null);
        addAccountCards(accounts, jsonObject);

        return accounts;
    }

    // MODIFIES: accounts
    // EFFECTS: parses AccountCards from JSONObject and adds them to accounts
    public void addAccountCards(AccountRepository accounts, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");

        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccountCard(accounts, nextAccount);
        }
    }

    // MODIFIES: accounts
    // EFFECTS: parses an AccountCard from JSONObject and adds it to accounts
    public void addAccountCard(AccountRepository accounts, JSONObject jsonObject) {
        String title = readTitle(jsonObject);
        String login = readLogin(jsonObject);
        String password = readPassword(jsonObject);
        String email = readEmail(jsonObject);
        String url = readUrl(jsonObject);
        String date = readDate(jsonObject);

        AccountCard accountCard = new AccountCard(title);
        accountCard.setLogin(login);
        accountCard.setPassword(password);
        accountCard.setEmail(email);
        accountCard.setUrl(url);
        accountCard.setDate(date);

        accounts.addCard(accountCard);
    }

    // EFFECTS: if key: "title" exists, returns the string under the key: "title", otherwise returns null
    public String readTitle(JSONObject jsonObject) {
        return jsonObject.getString("title");
    }

    // EFFECTS: if key: "login" exists, returns the string under the key: "login", otherwise returns null
    public String readLogin(JSONObject jsonObject) {
        if (jsonObject.has("login")) {
            return jsonObject.getString("login");
        } else {
            return null;
        }
    }

    // EFFECTS: if key: "password" exists, returns the string under the key: "password", otherwise returns null
    public String readPassword(JSONObject jsonObject) {
        if (jsonObject.has("password")) {
            return jsonObject.getString("password");
        } else {
            return null;
        }
    }

    // EFFECTS: if key: "email" exists, returns the string under the key: "email", otherwise returns null
    public String readEmail(JSONObject jsonObject) {
        if (jsonObject.has("email")) {
            return jsonObject.getString("email");
        } else {
            return null;
        }
    }

    // EFFECTS: if key: "url" exists, returns the string under the key: "url", otherwise returns null
    public String readUrl(JSONObject jsonObject) {
        if (jsonObject.has("url")) {
            return jsonObject.getString("url");
        } else {
            return null;
        }
    }

    // EFFECTS: if key: "url" exists, returns the string under the key: "url", otherwise returns null
    public String readDate(JSONObject jsonObject) {
        if (jsonObject.has("date")) {
            return jsonObject.getString("date");
        } else {
            return null;
        }
    }
}
