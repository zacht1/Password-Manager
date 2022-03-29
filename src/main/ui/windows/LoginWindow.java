package ui.windows;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import ui.PasswordManagerApp;
import ui.windows.popups.OverwriteAccountDialogBox;
import ui.windows.popups.WrongPasswordDialogBox;
import ui.windows.tools.PasswordPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// represents the login window of the application
public class LoginWindow extends JFrame implements ActionListener, KeyListener {
    private static final String LOGO_FILE = "images/blue_logo.png";

    private final PasswordManagerApp passwordManagerApp;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton settingsButton;
    private final JLabel iconLabel;
    private final ImageIcon icon;
    private final JCheckBox checkBox;
    private final String masterPassword;


    // EFFECTS: creates the login window
    public LoginWindow(String masterPassword, PasswordManagerApp passwordManagerApp) {
        this.passwordManagerApp = passwordManagerApp;
        this.masterPassword = masterPassword;
        passwordField = new JPasswordField();
        loginButton = new JButton();
        settingsButton = new JButton();
        iconLabel = new JLabel();
        icon = new ImageIcon(LOGO_FILE);
        checkBox = new JCheckBox();
        runWindow();
    }

    // MODIFIES: this
    // EFFECTS: instantiates fields, and runs the login window and all its components
    public void runWindow() {
        setupPasswordField();
        setupButtons();
        setupIcon();
        setupCheckBox();
        setupWindow();
    }

    // MODIFIES: passwordField
    // EFFECTS: sets up all necessary attributes of passwordField
    private void setupPasswordField() {
        new PasswordPrompt("Enter your password", passwordField);
        passwordField.setBounds(107, 205, 210, 28);
        passwordField.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField.setToolTipText("Enter master password");
        passwordField.addKeyListener(this);
        passwordField.addActionListener(this);
    }

    // MODIFIES: passwordField
    // EFFECTS: shows the password in plain text
    private void showPassword() {
        passwordField.setFont(new Font("Courier New", Font.PLAIN, 14));
        passwordField.setEchoChar('\u0000');
    }

    // MODIFIES: passwordField
    // EFFECTS: hides the password, by setting echo character to '•'
    private void hidePassword() {
        passwordField.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField.setEchoChar('•');
    }

    // MODIFIES: loginButton
    // EFFECTS: sets up all necessary attributes of loginButton
    private void setupButtons() {
        loginButton.setBounds(327,205,75,28);
        loginButton.setText("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        loginButton.addActionListener(this);

        settingsButton.setBounds(476, 4,20,20);
        settingsButton.setIcon(new FlatSVGIcon("ui/icons/add.svg"));
        settingsButton.setToolTipText("Create new account");
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusable(false);
        settingsButton.addActionListener(this);
    }

    // MODIFIES: iconLabel
    // EFFECTS: sets up all necessary attributes of iconLabel
    private void setupIcon() {
        iconLabel.setBounds(150,2,200,200);
        iconLabel.setIcon(icon);
        iconLabel.setOpaque(true);
    }

    // MODIFIES: checkBox
    // EFFECTS: sets up all necessary attributes of the show password check-box
    private void setupCheckBox() {
        checkBox.setBounds(107,240,117,15);
        checkBox.setText("Show password");
        checkBox.setFocusable(false);
        checkBox.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets up all necessary attributes of the login window
    private void setupWindow() {
        this.getRootPane().setDefaultButton(loginButton);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500,350);
        this.setLocationRelativeTo(null);
        this.setTitle("MyPasswordManager");
        this.setResizable(false);
        this.setLayout(null);
        this.add(settingsButton);
        this.add(passwordField);
        this.add(loginButton);
        this.add(iconLabel);
        this.add(checkBox);
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: checks if the password is correct, if it is then it opens the main window, if it isn't then it
    //          displays a pop-up informing user that the password is incorrect
    public void checkPassword() {
        if (passwordField.getPassword().length > 0) {
            if (String.valueOf(passwordField.getPassword()).equals(masterPassword)) {
                passwordManagerApp.runMainWindow();
                this.dispose();
            } else {
                new WrongPasswordDialogBox("Wrong password",this);
            }
        }
    }

    // getters & setters
    public PasswordManagerApp getPasswordManagerApp() {
        return passwordManagerApp;
    }


    /**
     * Invoked when an action occurs.
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (loginButton.equals(e.getSource())) {
            checkPassword();
        }
        if (checkBox.isSelected()) {
            showPassword();
        } else {
            hidePassword();
        }
        if (settingsButton.equals(e.getSource())) {
            new OverwriteAccountDialogBox(this);
        }
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            checkPassword();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }
}