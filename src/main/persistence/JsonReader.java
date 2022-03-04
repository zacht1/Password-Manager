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

// represents a reader, which reads AccountRepository from source file
// Credit: This class is based on the code from the JsonReader class in JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads AccountRepository from file and returns it, throws IOException if cannot read from source file
    public AccountRepository readAccounts() throws IOException {
        String data = readString(source);
        JSONObject jsonObject = new JSONObject(data);
        return parseAccountRepository(jsonObject);
    }

    // EFFECTS: reads the source file as a string a returns it, throws IOException if cannot read from source file
    public String readString(String source) throws IOException {
        StringBuilder dataBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(dataBuilder::append);
        }

        return dataBuilder.toString();
    }

    // EFFECTS: reads AccountRepository from JSONObject and returns it
    public AccountRepository parseAccountRepository(JSONObject jsonObject) {
        String password = jsonObject.getString("password");
        AccountRepository accounts = new AccountRepository(password);
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

        AccountCard accountCard = new AccountCard(title);
        accountCard.setLogin(login);
        accountCard.setPassword(password);
        accountCard.setEmail(email);
        accountCard.setUrl(url);

        accounts.addCard(accountCard);
    }

    // EFFECTS: if key: "title" exists, returns the string under the key: "title", otherwise returns null
    public String readTitle(JSONObject jsonObject) {
        if (jsonObject.has("title")) {
            return jsonObject.getString("title");
        } else {
            return null;
        }
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


}
