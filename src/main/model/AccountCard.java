package model;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

// Represents an account card with a title, login, password, email and website URL
public class AccountCard {
    private String title;
    private String login;
    private String password;
    private String email;
    private String url;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public AccountCard(String title) {
        this.title = title;
        login = null;
        password = null;
        email = null;
        url = null;
    }

    // EFFECTS: if AccountCard has a password, it is copied to the clipboard and returns true, otherwise returns false
    public boolean copyPassword() {
        if (password != null) {
            StringSelection myStringSelection = new StringSelection(password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(myStringSelection, null);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: if AccountCard has a login, it is copied to the clipboard and returns true, otherwise returns false
    public boolean copyLogin() {
        if (login != null) {
            StringSelection myStringSelection = new StringSelection(login);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(myStringSelection, null);
            return true;
        } else {
            return false;
        }
    }

    // getters & setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
