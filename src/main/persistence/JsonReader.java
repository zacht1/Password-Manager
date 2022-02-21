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
        String title = jsonObject.getString("title");
        String login = jsonObject.getString("login");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");
        String url = jsonObject.getString("url");

        AccountCard accountCard = new AccountCard(title);
        accountCard.setLogin(login);
        accountCard.setPassword(password);
        accountCard.setEmail(email);
        accountCard.setUrl(url);

        accounts.addCard(accountCard);
    }
}
