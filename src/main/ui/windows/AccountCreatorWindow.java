package ui.windows;

import ui.PasswordManagerApp;
import ui.windows.popups.PasswordMatchDialogBox;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

// represents the creation window for a new MyPasswordManager account
public class AccountCreatorWindow extends JFrame implements ActionListener, KeyListener,
        FocusListener, DocumentListener {
    private static final String LOGO_FILE = "images/blue_logo_small.png";

    private final PasswordManagerApp passwordManagerApp;

    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JButton createButton;
    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel iconLabel;
    private ImageIcon icon;
    private JCheckBox checkBox;
    private JLabel passwordPrompt1;
    private JLabel passwordPrompt2;
    private Document doc1;
    private Document doc2;


    // EFFECTS: creates the login window
    public AccountCreatorWindow(PasswordManagerApp passwordManagerApp) {
        this.passwordManagerApp = passwordManagerApp;
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
        passwordField1.setBounds(125, 172, 250, 28);
        passwordField1.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField1.setToolTipText("Enter master password");
        passwordField1.addKeyListener(this);
        passwordField1.addActionListener(this);

        passwordField2.setBounds(125, 205, 250, 28);
        passwordField2.setFont(new Font("Dialog", Font.BOLD, 14));
        passwordField2.setToolTipText("Enter master password");
        passwordField2.addKeyListener(this);
        passwordField2.addActionListener(this);

        addPasswordPrompts();
    }

    // MODIFIES: passwordPrompt1, passwordPrompt2, doc1, doc2
    // EFFECTS: setup passwordPrompts and all necessary attributes
    // credit: reformatted code from here:
    // http://tips4java.wordpress.com/2009/11/29/text-prompt - Rob Camick
    private void addPasswordPrompts() {
        passwordPrompt1 = new JLabel("Enter password");
        passwordPrompt2 = new JLabel("Confirm password");

        doc1 = passwordField1.getDocument();
        doc2 = passwordField2.getDocument();

        passwordPrompt1.setFont(new Font("Courier New", Font.PLAIN, 12));
        passwordPrompt1.setForeground(new Color(100, 105, 121));
        passwordPrompt1.setBorder(new EmptyBorder(new Insets(5,3,5,9)));
        passwordPrompt1.setHorizontalAlignment(SwingConstants.LEFT);

        passwordPrompt2.setFont(new Font("Courier New", Font.PLAIN, 12));
        passwordPrompt2.setForeground(new Color(100, 105, 121));
        passwordPrompt2.setBorder(new EmptyBorder(new Insets(5,3,5,9)));
        passwordPrompt2.setHorizontalAlignment(SwingConstants.LEFT);

        passwordField1.addFocusListener(this);
        passwordField2.addFocusListener(this);
        doc1.addDocumentListener(this);
        doc2.addDocumentListener(this);

        passwordField1.setLayout(new BorderLayout());
        passwordField1.add(passwordPrompt1);

        passwordField2.setLayout(new BorderLayout());
        passwordField2.add(passwordPrompt2);

        showPrompt();
    }

    // MODIFIES: passwordPrompt
    // EFFECTS: check to see if there is text in the passwordField, if there is remove passwordPrompt
    private void showPrompt() {
        passwordPrompt1.setVisible(doc1.getLength() <= 0);

        passwordPrompt2.setVisible(doc2.getLength() <= 0);
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

    // MODIFIES: titleLabel, subtitleLabel
    // EFFECTS: sets up all necessary attributes of titleLabel and subtitleLabel
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
        checkBox.setBounds(125,156,130,16);
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

    // EFFECTS: if a password was entered, and the two password fields contain the same password,
    //          then close this window and set up new account
    private void checkPassword() {
        if (passwordField1.getPassword().length > 0) {
            if (Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword())) {
                this.dispose();
                passwordManagerApp.setupNewAccount(String.valueOf(passwordField1.getPassword()));
            } else {
                new PasswordMatchDialogBox(this);
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

    @Override
    public void focusGained(FocusEvent e) {
        showPrompt();
    }

    @Override
    public void focusLost(FocusEvent e) {
        showPrompt();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        showPrompt();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        showPrompt();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // not used
    }
}
