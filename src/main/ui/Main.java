package ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;

import java.io.FileNotFoundException;

// runs MyPasswordManager
public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        new PasswordManagerApp();
        //        new LoginWindow();
    }
}