package ui;

import java.io.FileNotFoundException;

// runs MyPasswordManager
public class Main {
    public static void main(String[] args) {
        try {
            new PasswordManagerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Backup not found");
        }
    }
}