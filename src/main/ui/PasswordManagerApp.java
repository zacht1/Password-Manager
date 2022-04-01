package ui;

import model.AccountRepository;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.windows.AccountCreatorWindow;
import ui.windows.LoginWindow;
import ui.windows.MainWindow;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents the passwordManager app
public class PasswordManagerApp {
    private static final String FILE_PATHNAME_ACCOUNTS = "./data/myPasswordManager.json";
    private static final String FILE_PATHNAME_PASSWORD = "./data/myPasswordManagerPassword.json";

    private AccountRepository passwordManager;
    private MainWindow mainWindow;
    private JsonWriter jsonWriter;

    // EFFECTS: creates password manager app
    public PasswordManagerApp() {
        runPasswordManager();

        InputMap im = (InputMap)UIManager.get("Button.focusInputMap");
        im.put(KeyStroke.getKeyStroke("pressed SPACE"), "none");
        im.put(KeyStroke.getKeyStroke("released SPACE"), "none");
    }

    // EFFECTS: runs password manager app
    private void runPasswordManager() {
        jsonWriter = new JsonWriter(FILE_PATHNAME_ACCOUNTS, FILE_PATHNAME_PASSWORD);
        JsonReader jsonReader = new JsonReader(FILE_PATHNAME_ACCOUNTS, FILE_PATHNAME_PASSWORD);

        if (accountExists()) {
            loadFromSave(jsonReader);
            runLoginWindow();
        } else {
            runAccountCreatorWindow();
        }
    }

    // EFFECTS: returns true if there is an existing password manager account, false otherwise
    private Boolean accountExists() {
        File jsonAccountsFile = new File(FILE_PATHNAME_ACCOUNTS);
        File jsonPasswordFile = new File(FILE_PATHNAME_PASSWORD);
        return jsonAccountsFile.exists() && jsonPasswordFile.exists();
    }

    // MODIFIES: this
    // EFFECTS: loads account repository from saved file
    private void loadFromSave(JsonReader jsonReader) {
        try {
            passwordManager = jsonReader.readAccounts();
            passwordManager.setMasterPassword(jsonReader.readMasterPassword());
        } catch (IOException e) {
            System.out.println("Could not find file");
        }
    }

    // MODIFIES: jsonWriter
    // EFFECTS: saves the current passwordManager to file
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(passwordManager);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file: " + FILE_PATHNAME_ACCOUNTS);
        }
    }

    // EFFECTS: runs the login window
    private void runLoginWindow() {
        new LoginWindow(passwordManager.getMasterPassword(), this);
    }

    // EFFECTS: runs the account creator window
    public void runAccountCreatorWindow() {
        new AccountCreatorWindow(this);
    }

    // EFFECTS: runs the main password manager window
    public void runMainWindow() {
        mainWindow = new MainWindow(passwordManager.getAccounts(), this);
    }

    // MODIFIES: passwordManager, mainWindow
    // EFFECTS: sets up a new MyPasswordManager account
    public void setupNewAccount(String password) {
        passwordManager = new AccountRepository(password);
        mainWindow = new MainWindow(passwordManager.getAccounts(), this);
    }

    // MODIFIES: mainWindow
    // EFFECTS: logs out of main window, and runs the login window
    public void logout() {
        mainWindow.dispose();
        runLoginWindow();
    }

    // EFFECTS: returns the account repository
    public AccountRepository getPasswordManager() {
        return passwordManager;
    }
}