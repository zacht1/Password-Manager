package ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

// runs MyPasswordManager
public class Main {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        new PasswordManagerApp();
    }
}
