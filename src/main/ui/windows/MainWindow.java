package ui.windows;

import model.AccountCard;
import model.logging.EventLog;
import model.logging.Event;
import ui.PasswordManagerApp;
import ui.windows.panels.CardListPanel;
import ui.windows.panels.CardViewPanel;
import ui.windows.popups.DeleteConfirmationDialogBox;
import ui.windows.popups.SuccessfulSaveDialogBox;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

// represents the main window of myPasswordManager app
public class MainWindow extends JFrame implements ActionListener {
    private CardListPanel cardListPanel;
    private CardViewPanel cardViewPanel;
    private JToolBar toolBar;

    private final PasswordManagerApp passwordManagerApp;

    private List<AccountCard> accounts;
    private AccountCard selectedAccount;

    private Boolean creatorWindowOpen;
    private Boolean editorWindowOpen;

    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private JButton lockButton;
    private JButton generatorButton;
    private JButton sortButton;
    private JButton settingsButton;
    private JButton saveButton;

    private int sortButtonTimesPushed;

    // MODIFIES: this
    // EFFECTS: creates a main window with given accounts, given app,
    //          and sets the selected account to be the first in the list
    public MainWindow(List<AccountCard> accounts, PasswordManagerApp passwordManagerApp) {
        this.accounts = accounts;
        this.passwordManagerApp = passwordManagerApp;
        this.creatorWindowOpen = false;
        this.editorWindowOpen = false;
        this.sortButtonTimesPushed = 0;

        if (accounts.size() <= 0) {
            selectedAccount = null;
        } else {
            selectedAccount = accounts.get(0);
        }

        runWindow();
    }

    // EFFECTS: runs the main window
    public void runWindow() {
        setupToolBar();
        setupPanels();
        setupWindow();
    }

    // MODIFIES: toolBar
    // EFFECTS: instantiates toolBar and sets up all necessary components and attributes
    private void setupToolBar() {
        setupToolBarButtons();

        toolBar = new JToolBar();
        toolBar.setBackground(new Color(56, 88, 126));
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(sortButton);
        toolBar.add(generatorButton);
        toolBar.addSeparator();
        toolBar.add(lockButton);
        toolBar.add(settingsButton);
    }

    // EFFECTS: sets up all buttons on toolBar
    private void setupToolBarButtons() {
        setupAddButton();
        setupDeleteButton();
        setupEditButton();
        setupGeneratorButton();
        setupLockButton();
        setupSortButton();
        setupSettingsButton();
        setupSaveButton();
    }

    // MODIFIES: addButton
    // EFFECTS: instantiates addButton and sets up all necessary attributes
    private void setupAddButton() {
        addButton = new JButton();
        addButton.setIcon(new ImageIcon("./images/icons/add_file.png"));
        addButton.setBorderPainted(false);
        addButton.setToolTipText("Add new card");
        addButton.addActionListener(this);
    }

    // MODIFIES: editButton
    // EFFECTS: instantiates editButton and sets up all necessary attributes
    private void setupEditButton() {
        editButton = new JButton();
        editButton.setIcon(new ImageIcon("./images/icons/edit.png"));
        editButton.setBorderPainted(false);
        editButton.setToolTipText("Edit card");
        editButton.addActionListener(this);
    }

    // MODIFIES: deleteButton
    // EFFECTS: instantiates deleteButton and sets up all necessary attributes
    private void setupDeleteButton() {
        deleteButton = new JButton();
        deleteButton.setIcon(new ImageIcon("./images/icons/garbage.png"));
        deleteButton.setBorderPainted(false);
        deleteButton.setToolTipText("Delete card");
        deleteButton.addActionListener(this);
    }

    // MODIFIES: lockButton
    // EFFECTS: instantiates lockButton and sets up all necessary attributes
    private void setupLockButton() {
        lockButton = new JButton();
        lockButton.setIcon(new ImageIcon("./images/icons/lock.png"));
        lockButton.setBorderPainted(false);
        lockButton.setToolTipText("Lock myPasswordManager");
        lockButton.addActionListener(this);
    }

    // MODIFIES: settingsButton
    // EFFECTS: instantiates settingsButton and sets up all necessary attributes
    private void setupSettingsButton() {
        settingsButton = new JButton();
        settingsButton.setIcon(new ImageIcon("./images/icons/settings.png"));
        settingsButton.setBorderPainted(false);
        settingsButton.setToolTipText("Preferences");
        settingsButton.addActionListener(this);
    }

    // MODIFIES: generatorButton
    // EFFECTS: instantiates generatorButton and sets up all necessary attributes
    private void setupGeneratorButton() {
        generatorButton = new JButton();
        generatorButton.setIcon(new ImageIcon("./images/icons/key.png"));
        generatorButton.setBorderPainted(false);
        generatorButton.setToolTipText("Generate password");
        generatorButton.addActionListener(this);
    }

    // MODIFIES: sortButton
    // EFFECTS: instantiates sortButton and sets up all necessary attributes
    private void setupSortButton() {
        sortButton = new JButton();
        sortButton.setIcon(new ImageIcon("./images/icons/sort.png"));
        sortButton.setBorderPainted(false);
        sortButton.setToolTipText("Sort accounts");
        sortButton.addActionListener(this);
    }

    // MODIFIES: saveButton
    // EFFECTS: instantiates saveButton and sets up all necessary attributes
    private void setupSaveButton() {
        saveButton = new JButton();
        saveButton.setIcon(new ImageIcon("./images/icons/save_file.png"));
        saveButton.setBorderPainted(false);
        saveButton.setToolTipText("Save");
        saveButton.addActionListener(this);
    }

    // MODIFIES: cardListPanel, cardViewPanel
    // EFFECTS: instantiates the card viewer and card list panel
    public void setupPanels() {
        cardListPanel = new CardListPanel(accounts, this);
        if (accounts.size() > 0) {
            cardViewPanel = new CardViewPanel(selectedAccount);
        } else {
            cardViewPanel = new CardViewPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the main window with all necessary attributes and components
    private void setupWindow() {
        JLabel label = new JLabel();
        setPreferredSize(new Dimension(800,2));
        label.setBorder(new MatteBorder(0,0,1,0,Color.BLUE));

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(900, 700);
        this.setMinimumSize(new Dimension(700,650));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(toolBar, BorderLayout.NORTH);
        this.add(cardListPanel, BorderLayout.WEST);
        this.add(cardViewPanel, BorderLayout.CENTER);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                passwordManagerApp.save();
                printLog();
                EventLog.getInstance().clear();
                System.exit(0);
            }

        });
    }

    // MODIFIES: this
    // EFFECTS: updates the card viewer panel
    public void updateCardViewer() {
        this.remove(cardViewPanel);
        cardViewPanel = new CardViewPanel(selectedAccount);
        this.add(cardViewPanel, BorderLayout.CENTER);
        this.repaint();
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: updates the card viewer panel
    public void updateCardList() {
        this.remove(cardListPanel);
        cardListPanel = new CardListPanel(accounts, this);
        this.add(cardListPanel, BorderLayout.WEST);
        this.repaint();
        this.setVisible(true);
    }

    // EFFECTS: opens AccountCreatorWindow, in order to add a new card
    private void addCard() {
        creatorWindowOpen = true;
        new CardCreatorWindow(this);
    }

    // EFFECTS: opens AccountEditorWindow, in order to edit current selected card
    private void editCard() {
        editorWindowOpen = true;
        new CardEditorWindow(this);
    }

    // EFFECTS: deletes the current selected card
    public void deleteCard() {
        passwordManagerApp.getPasswordManager().deleteCard(selectedAccount);
        accounts = passwordManagerApp.getPasswordManager().getAccounts();

        if (accounts.size() <= 0) {
            selectedAccount = null;
            this.remove(cardViewPanel);
            cardViewPanel = new CardViewPanel();
            this.add(cardViewPanel);
            this.repaint();
            this.setVisible(true);
        } else {
            selectedAccount = accounts.get(0);
            updateCardViewer();
        }
        updateCardList();
    }

    // EFFECTS: prints the all logs to the screen
    private void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n");
        }
    }

    // MODIFIES: cardListPanel
    // EFFECTS: changes the way the list of account card buttons are sorted in the CardListPanel
    private void changeListSort() {
        if (sortButtonTimesPushed % 2 == 1) {
            cardListPanel.setAccounts(cardListPanel.sortAlphabetically(passwordManagerApp.getPasswordManager()
                    .getAccounts()));
        } else {
            cardListPanel.setAccounts(cardListPanel.sortRecent(passwordManagerApp.getPasswordManager()
                    .getAccounts()));
        }
        cardListPanel.getCardList().removeAll();
        cardListPanel.addCards();
        cardListPanel.revalidate();
        cardListPanel.repaint();
        sortButtonTimesPushed++;
    }

    //getters & setters
    public CardListPanel getCardListPanel() {
        return cardListPanel;
    }

    public void setCreatorWindowOpen(Boolean open) {
        this.creatorWindowOpen = open;
    }

    public void setEditorWindowOpen(Boolean open) {
        this.editorWindowOpen = open;
    }

    public void setSelectedAccount(AccountCard account) {
        this.selectedAccount = account;
    }

    public PasswordManagerApp getPasswordManagerApp() {
        return passwordManagerApp;
    }

    public AccountCard getSelectedAccount() {
        return selectedAccount;
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (addButton.equals(e.getSource())) {
            if (!creatorWindowOpen) {
                addCard();
            }
        }
        if (editButton.equals(e.getSource())) {
            if (!editorWindowOpen) {
                editCard();
            }
        }
        if (deleteButton.equals(e.getSource())) {
            new DeleteConfirmationDialogBox(this);
        }
        if (lockButton.equals(e.getSource())) {
            passwordManagerApp.logout();
        }
        if (saveButton.equals(e.getSource())) {
            passwordManagerApp.save();
            new SuccessfulSaveDialogBox(this);
        }
        if (sortButton.equals(e.getSource())) {
            changeListSort();
        }
    }
}
