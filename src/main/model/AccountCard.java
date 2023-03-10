package model;

import org.json.JSONObject;
import persistence.JsonFormat;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

// Represents an account card with a title, login, password, email and website URL
public class AccountCard implements JsonFormat {
    private String title;
    private String login;
    private String password;
    private String email;
    private String url;
    private Date date;

    // REQUIRES: title has a non-zero length
    // EFFECTS: constructs a new account card with the given title and null for the rest of the fields
    public AccountCard(String title) {
        this.title = title;
        login = null;
        password = null;
        email = null;
        url = null;
        date = new Date();
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

    // MODIFIES: this
    // EFFECTS: sets password to a random password from a combination of letters, numbers and symbols and returns
    //          that password
    // note: this is not a great password generator, needs improvement
    // credit: reformatted some code from here:
    // https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
    public String generatePassword(int length) {
        Random rand = new Random();
        String characters;
        characters = "1234567890" + "1234567890" + "abcdefghijklmnopqrstuvwxyz"
                + "abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz"  + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "!!!@##$**&*!&@$%!%^&*()_+={}|[];:<>?/";
        ArrayList<Character> chars = new ArrayList<>();
        int i = 0;

        while (i < length) {
            chars.add(characters.charAt(rand.nextInt(characters.length())));
            i++;
        }

        String password = chars.stream().map(Object::toString).collect(Collectors.joining());
        this.password = password;
        return password;
    }

    @Override
    // credit: this method is based on the toJson method in JsonSerializationDemo Thingy class
    public JSONObject formatJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("login", login);
        jsonObject.put("password", password);
        jsonObject.put("email", email);
        jsonObject.put("url", url);
        jsonObject.put("date", date);

        return jsonObject;
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

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        if (!(date == null)) {
            try {
                this.date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(date);
            } catch (ParseException e) {
                System.out.println("Unparseable date");
            }
        }
    }
}