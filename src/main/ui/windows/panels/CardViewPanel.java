package ui.windows.panels;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.AccountCard;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the panel which shows the selected card and its fields
public class CardViewPanel extends JPanel implements ActionListener {
    private final AccountCard card;

    private final JPanel titlePanel;
    private final JPanel bottomPanel;
    private final JPanel rightPanel;
    private final JPanel leftPanel;
    private final JPanel centrePanel;
    private final JPanel loginPanel;
    private final JPanel passwordPanel;
    private final JPanel emailPanel;
    private final JPanel urlPanel;

    private final JLabel title;
    private final JLabel login;
    private final JLabel password;
    private final JLabel email;
    private final JLabel url;

    private JToggleButton button;

    // EFFECTS: initializes all fields, sets the card to be displayed to the given card, and runs the panel
    public CardViewPanel(AccountCard card) {
        this.card = card;

        this.titlePanel = new JPanel();
        this.bottomPanel = new JPanel();
        this.rightPanel = new JPanel();
        this.leftPanel = new JPanel();
        this.centrePanel = new JPanel();
        this.loginPanel = new JPanel();
        this.passwordPanel = new JPanel();
        this.emailPanel = new JPanel();
        this.urlPanel = new JPanel();

        this.title = new JLabel(card.getTitle());
        this.login = new JLabel(card.getLogin());
        this.password = new JLabel();
        this.email = new JLabel(card.getEmail());
        this.url = new JLabel(card.getUrl());

        runCardViewPanel();
    }

    // EFFECTS: creates new panel with all necessary attributes and components
    private void runCardViewPanel() {
        setupTitlePanel();
        setupSpacingPanels();
        setupCentrePanel();
        setupMainPanel();
    }

    // MODIFIES: titlePanel, title
    // EFFECTS: creates new title panel with all necessary attributes and components
    private void setupTitlePanel() {
        titlePanel.setPreferredSize(new Dimension(900, 100));
        titlePanel.setLayout(null);
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 30));
        title.setBounds(40,33,300,34);

        titlePanel.add(title);
    }

    // MODIFIES: rightPanel, leftPanel, bottomPanel
    // EFFECTS: creates right, left, and bottom panels for spacing
    private void setupSpacingPanels() {
        rightPanel.setPreferredSize(new Dimension(50, 700));
        leftPanel.setPreferredSize(new Dimension(50,700));
        bottomPanel.setPreferredSize(new Dimension(900, 120));
    }

    // MODIFIES: centrePanel
    // EFFECTS: sets up centre panel with all necessary attributes and components
    private void setupCentrePanel() {
        setupPasswordPanel();
        setupTextPanels();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        centrePanel.add(loginPanel);
        centrePanel.add(passwordPanel);
        centrePanel.add(emailPanel);
        centrePanel.add(urlPanel);
        centrePanel.add(new JPanel());
        centrePanel.add(new JPanel());
    }

    // EFFECTS: sets up all panels that display simple text
    private void setupTextPanels() {
        setupLoginPanel();
        setupEmailPanel();
        setupUrlPanel();
    }

    // MODIFIES: login, loginPanel
    // EFFECTS: sets up the login panel with all necessary attributes and components
    private void setupLoginPanel() {
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
        loginLabel.setForeground(new Color(105, 107, 114));
        loginLabel.setBounds(0,4,100,15);

        login.setBounds(10,30,500,20);
        login.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        loginPanel.setBorder(new MatteBorder(0,0,1,0,new Color(78, 79, 82)));
        loginPanel.setLayout(null);
        loginPanel.add(loginLabel);
        loginPanel.add(login);
    }

    // MODIFIES: email, emailPanel
    // EFFECTS: sets up the email panel with all necessary attributes and components
    private void setupEmailPanel() {
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
        emailLabel.setForeground(new Color(105, 107, 114));
        emailLabel.setBounds(0,4,100,10);

        email.setBounds(10,30,500,20);
        email.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        emailPanel.setBorder(new MatteBorder(0,0,1,0,new Color(78, 79, 82)));
        emailPanel.setLayout(null);
        emailPanel.add(emailLabel);
        emailPanel.add(email);
    }

    // MODIFIES: url, urlPanel
    // EFFECTS: sets up the url panel with all necessary attributes and components
    private void setupUrlPanel() {
        JLabel urlLabel = new JLabel("Website URL");
        urlLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
        urlLabel.setForeground(new Color(105, 107, 114));
        urlLabel.setBounds(0,4,100,10);

        url.setBounds(10,30,500,20);
        url.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));

        urlPanel.setBorder(new MatteBorder(0,0,1,0,new Color(78, 79, 82)));
        urlPanel.setLayout(null);
        urlPanel.add(urlLabel);
        urlPanel.add(url);
    }

    // MODIFIES: button, password, passwordPanel
    // EFFECTS: sets up the password panel with all necessary attributes and components
    private void setupPasswordPanel() {
        button = new JToggleButton();
        button.setIcon(new FlatSVGIcon("ui/icons/show_dark.svg"));
        button.setBounds(500, 18, 28,28);
        button.putClientProperty("JButton.buttonType", "borderless");
        button.addActionListener(this);
        button.setFocusable(false);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Helvetica Neue", Font.PLAIN, 11));
        passwordLabel.setForeground(new Color(105, 107, 114));
        passwordLabel.setBounds(0,4,100,10);

        password.setBounds(10,30,500,20);
        hidePassword();

        passwordPanel.setBorder(new MatteBorder(0,0,1,0,new Color(78, 79, 82)));
        passwordPanel.setLayout(null);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(button);
        passwordPanel.add(password);
    }

    // MODIFIES: password
    // EFFECTS: hides the displayed password by setting its field to a string of "•"
    private void hidePassword() {
        if (card.getPassword().length() == 0) {
            password.setText(card.getPassword());
        } else {
            password.setText("• • • • • • • • • • • •");
            password.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        }
    }

    // MODIFIES: password
    // EFFECTS: shows the password by setting its field to plain text
    private void showPassword() {
        password.setText(card.getPassword());
        password.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
    }

    // MODIFIES: this
    // EFFECTS: sets up the main panel with all necessary attributes and components
    private void setupMainPanel() {
        this.setLayout(new BorderLayout());
        this.add(rightPanel, BorderLayout.WEST);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.EAST);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(centrePanel, BorderLayout.CENTER);
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.equals(e.getSource()) && button.isSelected()) {
            showPassword();
            this.repaint();
        } else if (button.equals(e.getSource())) {
            hidePassword();
            this.repaint();
        }
    }
}
