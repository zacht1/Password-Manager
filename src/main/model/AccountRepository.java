package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Represents a list of all the account cards in the masterPassword manager, and a masterPassword to access the list
public class AccountRepository implements JsonFormat {
    private List<AccountCard> accounts;
    private String masterPassword;

    // EFFECTS: initializes AccountRepository with an empty list of accounts
    public AccountRepository(String password) {
        this.masterPassword = password;
        accounts = new ArrayList<>();
    }

    // EFFECTS: returns the AccountCard with the given title if it is in the AccountRepository, otherwise returns null
    public AccountCard getSpecificCard(String title) {
        List<String> accountTitles;
        accountTitles = accounts.stream().map(AccountCard::getTitle).collect(Collectors.toList());

        if (accountTitles.contains(title)) {
            int index = accountTitles.indexOf(title);
            return accounts.get(index);
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an account card to the account repository
    public void addCard(AccountCard card) {
        accounts.add(card);
    }

    // EFFECTS: returns a list of all the account card titles
    public List<String> getAccountTitles() {
        return accounts.stream().map(AccountCard::getTitle).collect(Collectors.toList());
    }

    // MODIFIES: this
    // EFFECTS: if AccountCard exists in the AccountRepository with the given name then:
    //          deletes the AccountCard from the AccountRepository with the given title and returns true,
    //          otherwise returns false
    public boolean deleteSpecificCard(String title) {
        List<String> accountTitles;
        accountTitles = accounts.stream().map(AccountCard::getTitle).collect(Collectors.toList());

        if (accountTitles.contains(title)) {
            int index = accountTitles.indexOf(title);
            accounts.remove(index);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns the number of account cards in AccountRepository
    public int numAccounts() {
        return accounts.size();
    }

    @Override
    // credit: this method is based on the toJson method in JsonSerializationDemo WorkRoom class
    public JSONObject formatJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accounts", formatCardsJson());

        return jsonObject;
    }

    public JSONObject formatPasswordJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("masterPassword", masterPassword);
        return jsonObject;
    }

    // EFFECTS: returns list of AccountCards as a JSONArray
    // credit: this method is based on the thingiesToJson method in JsonSerializationDemo WorkRoom class
    private JSONArray formatCardsJson() {
        JSONArray jsonArray = new JSONArray();

        for (AccountCard c : accounts) {
            jsonArray.put(c.formatJson());
        }

        return jsonArray;
    }

    // getters & setters
    public List<AccountCard> getAccounts() {
        return accounts;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
}