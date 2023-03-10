package model;

import model.logging.Event;
import model.logging.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Represents a list of all the account cards in the masterPassword manager, and a masterPassword to access the list
public class AccountRepository implements JsonFormat {
    private final List<AccountCard> accounts;
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
    // EFFECTS: adds given account card to the account repository
    public void addCard(AccountCard card) {
        accounts.add(card);
    }

    // MODIFIES: accounts
    // EFFECTS: adds given account card to the account repository, and logs the event
    public void addNewCard(AccountCard card) {
        accounts.add(card);
        EventLog.getInstance().logEvent(new Event(card.getTitle() + " card added to the account card repository"));
    }

    // EFFECTS: returns a list of all the account card titles
    public List<String> getAccountTitles() {
        return accounts.stream().map(AccountCard::getTitle).collect(Collectors.toList());
    }

    // MODIFIES: this
    // EFFECTS: if AccountCard exists in the AccountRepository with the given name then:
    //          deletes the AccountCard from the AccountRepository with the given title and returns true,
    //          otherwise returns false and logs the action
    public boolean deleteSpecificCard(String title) {
        List<String> accountTitles;
        accountTitles = accounts.stream().map(AccountCard::getTitle).collect(Collectors.toList());

        if (accountTitles.contains(title)) {
            int index = accountTitles.indexOf(title);
            accounts.remove(index);
            EventLog.getInstance().logEvent(new Event(title + " card deleted from the account card repository"));
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if AccountCard exists in the AccountRepository then, delete the card and return true,
    //          return false otherwise and logs the action
    public void deleteCard(AccountCard card) {
        if (accounts.contains(card)) {
            accounts.remove(card);
            EventLog.getInstance().logEvent(new Event(card.getTitle() + " card deleted from the account repository"));
        }
    }

    // MODIFIES: this
    // EFFECTS: if AccountCard exists in the AccountRepository then, delete the card and return true,
    //          return false otherwise
    public void deleteCardWithoutLogging(AccountCard card) {
        if (accounts.contains(card)) {
            accounts.remove(card);
        }
    }

    // EFFECTS: returns the number of account cards in AccountRepository
    public int numAccounts() {
        return accounts.size();
    }

    @Override
    // credit: this method is based on the toJson method in JsonSerializationDemo WorkRoom class
    // EFFECTS: adds accounts to JsonObject, and returns that JsonObject
    public JSONObject formatJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accounts", formatCardsJson());

        return jsonObject;
    }

    // credit: this method is based on the toJson method in JsonSerializationDemo WorkRoom class
    // EFFECTS: adds masterPassword to JsonObject, and returns that JsonObject
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