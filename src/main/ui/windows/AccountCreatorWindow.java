package ui.windows;

import ui.windows.popups.PasswordDialogBox;
import ui.windows.tools.PasswordPrompt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class AccountCreatorWindow extends JFrame implements ActionListener, KeyListener {
    private static final String LOGO_FILE = "./images/blue_logo_small.png";

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton createButton;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel iconLabel;
    private ImageIcon icon;
    private JCheckBox checkBox;


    // EFFECTS: creates the login window
    public AccountCreatorWindow() {
        runWindow();
    }

    // MODIFIES: this
    // EFFECTS: instantiates fields, and runs the login window and all its components
    private void runWindow() {
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        createButton = new JButton();
        iconLabel = new JLabel();
        titleLabel = new JLabel();
        subtitleLabel = new JLabel();
        icon = new ImageIcon(LOGO_FILE);
        checkBox = new JCheckBox();

        setupPasswordFields();
        setupLoginButton();
        setupIcon();
        setupCheckBox();
        setUpLabels();
        setupWindow();
    }

    // MODIFIES: passwordField1
    // EFFECTS: sets up all necessary attributes of passwordField1
    private void setupPasswordFields() {
        new PasswordPrompt("Enter password", passwordField1);
        passwordField1.setBounds(125, 172, 250, 28);
        passwordField1.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField1.setToolTipText("Enter master password");
        passwordField1.addKeyListener(this);
        passwordField1.addActionListener(this);

        new PasswordPrompt("Confirm password", passwordField2);
        passwordField2.setBounds(125, 205, 250, 28);
        passwordField2.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField2.setToolTipText("Enter master password");
        passwordField2.addKeyListener(this);
        passwordField2.addActionListener(this);
    }

    // MODIFIES: passwordField1
    // EFFECTS: shows the password in plain text
    private void showPassword() {
        passwordField1.setFont(new Font("Courier New", Font.PLAIN, 14));
        passwordField2.setFont(new Font("Courier New", Font.PLAIN, 14));
        passwordField1.setEchoChar('\u0000');
        passwordField2.setEchoChar('\u0000');
    }

    // MODIFIES: passwordField1
    // EFFECTS: hides the password, by setting echo character to '•'
    private void hidePassword() {
        passwordField1.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField2.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField1.setEchoChar('•');
        passwordField2.setEchoChar('•');
    }

    // MODIFIES: createButton
    // EFFECTS: sets up all necessary attributes of createButton
    private void setupLoginButton() {
        createButton.setBounds(125,238,120,28);
        createButton.setText("Create");
        createButton.setFont(new Font("Arial", Font.PLAIN, 12));
        createButton.addActionListener(this);
    }

    // MODIFIES: iconLabel
    // EFFECTS: sets up all necessary attributes of iconLabel
    private void setupIcon() {
        iconLabel.setBounds(50,20,100,100);
        iconLabel.setIcon(icon);
        iconLabel.setOpaque(true);
    }

    private void setUpLabels() {
        titleLabel.setBounds(130,35,500,50);
        titleLabel.setText("MyPasswordManager");
        titleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 25));

        subtitleLabel.setBounds(130, 75, 300,30);
        subtitleLabel.setText("Create your password database:");
        subtitleLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
    }

    // MODIFIES: checkBox
    // EFFECTS: sets up all necessary attributes of the show password check-box
    private void setupCheckBox() {
        checkBox.setBounds(125,152,117,15);
        checkBox.setText("Show password");
        checkBox.setFocusable(false);
        checkBox.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: sets up all necessary attributes of the login window
    private void setupWindow() {
        this.getRootPane().setDefaultButton(createButton);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500,350);
        this.setLocationRelativeTo(null);
        this.setTitle("MyPasswordManager");
        this.setResizable(false);
        this.setLayout(null);
        this.add(passwordField1);
        this.add(passwordField2);
        this.add(createButton);
        this.add(titleLabel);
        this.add(subtitleLabel);
        this.add(iconLabel);
        this.add(checkBox);
        this.setVisible(true);
    }

    // MODIFIES:
    // EFFECTS:
    private void checkPassword() {
        if (passwordField1.getPassword().length > 0) {
            if (Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {
                System.out.println("Correct!");
            } else {
                new PasswordDialogBox("Passwords don't match", this);
            }
        }
    }

    /**
     * Invoked when an action occurs.
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (createButton.equals(e.getSource())) {
            checkPassword();
        }
        if (checkBox.isSelected()) {
            showPassword();
        } else {
            hidePassword();
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
