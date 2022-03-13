package ui;

import ui.windows.LoginWindow;
import com.formdev.flatlaf.FlatDarculaLaf;

import java.io.FileNotFoundException;

// runs MyPasswordManager
public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        try {
            new PasswordManagerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Backup not found");
        }
//        new LoginWindow();
    }
}