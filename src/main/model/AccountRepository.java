package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Represents a list of all the account cards in the password manager, and a password to access the list
public class AccountRepository {
    private List<AccountCard> accounts;
    private String password;

    // EFFECTS: initializes AccountRepository with an empty list of accounts
    public AccountRepository(String password) {
        this.password = password;
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

    // getters & setters
    public List<AccountCard> getAccounts() {
        return accounts;
    }

    public String getPassword() {
        return password;
    }
}