package ui.windows;

import model.AccountCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// represents the card editing window
public class CardEditorWindow extends JFrame implements ActionListener {
    private JPanel centrePanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JLabel loginLabel;

    private JTextField titleField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JTextField loginField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel urlLabel;
    private JTextField urlField;
    private JButton saveButton;

    private final MainWindow mainWindow;

    // EFFECTS: creates the account card editor window with given passwordManagerApp and given AccountCard
    public CardEditorWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        runWindow();
    }

    // MODIFIES: this
    // EFFECTS: instantiates fields, and runs the login window and all its components
    private void runWindow() {
        setupTitleField();
        setupLoginComponents();
        setupPasswordComponents();
        setupEmailComponents();
        setupUrlComponents();
        setupSaveButton();
        setupCentrePanel();
        setupOtherPanels();
        setupWindow();
    }

    // MODIFIES: titleField
    // EFFECTS: sets up all necessary attributes of titleField
    private void setupTitleField() {
        titleField = new JTextField();
        titleField.setText(mainWindow.getSelectedAccount().getTitle());
        titleField.setBounds(40,25,420,30);
        titleField.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
    }

    // MODIFIES: loginField
    // EFFECTS: sets up all necessary attributes of loginField
    private void setupLoginComponents() {
        loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 11));
        loginLabel.setBounds(13, 6, 100, 15);

        loginField = new JTextField();
        loginField.setText(mainWindow.getSelectedAccount().getLogin());
        loginField.setBounds(10, 20, 400, 30);
        loginField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
    }

    // MODIFIES: passwordField
    // EFFECTS: sets up all necessary attributes of passwordField
    private void setupPasswordComponents() {
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 11));
        passwordLabel.setBounds(13, 61, 100, 15);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('\u0000');
        passwordField.setText(mainWindow.getSelectedAccount().getPassword());
        passwordField.setBounds(10, 75, 400, 30);
        passwordField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
    }

    // MODIFIES: emailField
    // EFFECTS: sets up all necessary attributes of emailField
    private void setupEmailComponents() {
        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 11));
        emailLabel.setBounds(13, 116, 100, 15);

        emailField = new JTextField();
        emailField.setText(mainWindow.getSelectedAccount().getEmail());
        emailField.setBounds(10, 130, 400, 30);
        emailField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
    }

    // MODIFIES: urlField
    // EFFECTS: sets up all necessary attributes of urlField
    private void setupUrlComponents() {
        urlLabel = new JLabel("Website");
        urlLabel.setFont(new Font("Helvetica Neue", Font.BOLD, 11));
        urlLabel.setBounds(13, 171, 100, 15);

        urlField = new JTextField();
        urlField.setText(mainWindow.getSelectedAccount().getUrl());
        urlField.setBounds(10, 185, 400, 30);
        urlField.setFont(new Font("Helvetica Neue", Font.PLAIN, 12));
    }

    // MODIFIES: saveButton
    // EFFECTS: sets up all necessary attributes of saveButton
    private void setupSaveButton() {
        saveButton = new JButton("Save & Close");
        saveButton.setBounds(343, 5, 120, 30);
        saveButton.addActionListener(this);
    }

    // MODIFIES: centrePanel, topPanel, bottomPanel, leftPanel, rightPanel
    // EFFECTS: sets up the all the panels and their necessary attributes and components
    private void setupCentrePanel() {
        centrePanel = new JPanel();

        centrePanel.setPreferredSize(new Dimension(200, 200));
        centrePanel.setBackground(lightenColor());
        centrePanel.setLayout(null);
        centrePanel.add(loginLabel);
        centrePanel.add(loginField);
        centrePanel.add(passwordLabel);
        centrePanel.add(passwordField);
        centrePanel.add(emailLabel);
        centrePanel.add(emailField);
        centrePanel.add(urlLabel);
        centrePanel.add(urlField);
    }

    // MODIFIES: topPanel, bottomPanel, leftPanel, rightPanel
    // EFFECTS: instantiates and sets up all top, bottom, right and left panels with all necessary attributes
    private void setupOtherPanels() {
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();

        topPanel.setPreferredSize(new Dimension(500, 80));
        topPanel.setLayout(null);
        topPanel.add(titleField);

        bottomPanel.setPreferredSize(new Dimension(500, 50));
        bottomPanel.setLayout(null);
        bottomPanel.add(saveButton);

        rightPanel.setPreferredSize(new Dimension(40, 450));

        leftPanel.setPreferredSize(new Dimension(40, 450));
    }

    // EFFECTS: returns the given rgb values color as a lighter shade of the same color
    private Color lightenColor() {
        double lightenConstant = 0.05;

        int red = this.getBackground().getRed();
        int green = this.getBackground().getGreen();
        int blue = this.getBackground().getBlue();

        int redDifference = 255 - red;
        int greenDifference = 255 - green;
        int blueDifference = 255 - blue;

        int newRed = (int) (redDifference * lightenConstant);
        int newGreen = (int) (greenDifference * lightenConstant);
        int newBlue = (int) (blueDifference * lightenConstant);

        return new Color(newRed + red, newGreen + green, newBlue + blue);
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame with all desired components
    private void setupWindow() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("MyPasswordManager");
        this.setLayout(new BorderLayout());
        this.add(centrePanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindow.setEditorWindowOpen(false);
            }

        });

    }

    // MODIFIES: newCard, mainWindow, passwordManagerApp
    // EFFECTS: updates the current card being edited in the mainWindow and the passwordManagerApp
    private void updateCard() {
        AccountCard newCard = new AccountCard(titleField.getText());

        updateCardTitle(newCard);
        updateCardLogin(newCard);
        updateCardPassword(newCard);
        updateCardEmail(newCard);
        updateCardUrl(newCard);

        mainWindow.getPasswordManagerApp().getPasswordManager()
                .deleteCardWithoutLogging(mainWindow.getSelectedAccount());
        mainWindow.getPasswordManagerApp().getPasswordManager().addCard(newCard);
        mainWindow.setSelectedAccount(newCard);
        mainWindow.updateCardViewer();
        mainWindow.updateCardList();
    }

    // EFFECTS: change title of given card to the value of TitleField
    private void updateCardTitle(AccountCard newCard) {
        newCard.setTitle(titleField.getText());
    }

    // EFFECTS: change login of given card to the value of LoginField
    private void updateCardLogin(AccountCard newCard) {
        newCard.setLogin(loginField.getText());
    }

    // EFFECTS: change password of given card to the value of passwordField
    private void updateCardPassword(AccountCard newCard) {
        newCard.setPassword(String.valueOf(passwordField.getPassword()));
    }

    // EFFECTS: change email of given card to the value of emailField
    private void updateCardEmail(AccountCard newCard) {
        newCard.setEmail(emailField.getText());
    }

    // EFFECTS: change url of given card to the value of urlField
    private void updateCardUrl(AccountCard newCard) {
        newCard.setUrl(urlField.getText());
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (saveButton.equals(e.getSource())) {
            updateCard();
        }
        mainWindow.setEditorWindowOpen(false);
        this.dispose();
    }
}