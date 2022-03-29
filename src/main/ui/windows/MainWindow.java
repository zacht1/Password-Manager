package ui.windows;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.AccountCard;
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
    private JPanel cardViewPanel;
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
    private JButton pinButton;
    private JButton settingsButton;
    private JButton saveButton;

    // MODIFIES: this
    // EFFECTS: creates a main window with given accounts, given app,
    //          and sets the selected account to be the first in thelist
    public MainWindow(List<AccountCard> accounts, PasswordManagerApp passwordManagerApp) {
        this.accounts = accounts;
        this.passwordManagerApp = passwordManagerApp;
        this.creatorWindowOpen = false;
        this.editorWindowOpen = false;

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
        toolBar.add(pinButton);
        toolBar.add(sortButton);
        toolBar.addSeparator();
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
        setupPinButton();
        setupSortButton();
        setupSettingsButton();
        setupSaveButton();
    }

    // MODIFIES: addButton
    // EFFECTS: instantiates addButton and sets up all necessary attributes
    private void setupAddButton() {
        addButton = new JButton();
        addButton.setIcon(new FlatSVGIcon("ui/icons/addFile_dark.svg"));
        addButton.setToolTipText("Add new card");
        addButton.addActionListener(this);
    }

    // MODIFIES: editButton
    // EFFECTS: instantiates editButton and sets up all necessary attributes
    private void setupEditButton() {
        editButton = new JButton();
        editButton.setIcon(new FlatSVGIcon("ui/icons/edit_dark.svg"));
        editButton.setToolTipText("Edit card");
        editButton.addActionListener(this);
    }

    // MODIFIES: deleteButton
    // EFFECTS: instantiates deleteButton and sets up all necessary attributes
    private void setupDeleteButton() {
        deleteButton = new JButton();
        deleteButton.setIcon(new FlatSVGIcon("ui/icons/gc.svg"));
        deleteButton.setToolTipText("Delete card");
        deleteButton.addActionListener(this);
    }

    // MODIFIES: lockButton
    // EFFECTS: instantiates lockButton and sets up all necessary attributes
    private void setupLockButton() {
        lockButton = new JButton();
        lockButton.setIcon(new FlatSVGIcon("ui/icons/lock_dark.svg"));
        lockButton.setToolTipText("Lock myPasswordManager");
        lockButton.addActionListener(this);
    }

    // MODIFIES: settingsButton
    // EFFECTS: instantiates settingsButton and sets up all necessary attributes
    private void setupSettingsButton() {
        settingsButton = new JButton();
        settingsButton.setIcon(new FlatSVGIcon("ui/icons/settings_dark.svg"));
        settingsButton.setToolTipText("Preferences");
        settingsButton.addActionListener(this);
    }

    // MODIFIES: generatorButton
    // EFFECTS: instantiates generatorButton and sets up all necessary attributes
    private void setupGeneratorButton() {
        generatorButton = new JButton();
        generatorButton.setIcon(new FlatSVGIcon("ui/icons/cwmPermissions_dark.svg"));
        generatorButton.setToolTipText("Generate password");
        generatorButton.addActionListener(this);
    }

    // MODIFIES: sortButton
    // EFFECTS: instantiates sortButton and sets up all necessary attributes
    private void setupSortButton() {
        sortButton = new JButton();
        sortButton.setIcon(new FlatSVGIcon("ui/icons/sortbyDuration_dark.svg"));
        sortButton.setToolTipText("Sort accounts");
        sortButton.addActionListener(this);
    }

    // MODIFIES: pinButton
    // EFFECTS: instantiates pinButton and sets up all necessary attributes
    private void setupPinButton() {
        pinButton = new JButton();
        pinButton.setIcon(new FlatSVGIcon("ui/icons/pin_dark.svg"));
        pinButton.setToolTipText("Pin card");
        pinButton.addActionListener(this);
    }

    // MODIFIES: saveButton
    // EFFECTS: instantiates saveButton and sets up all necessary attributes
    private void setupSaveButton() {
        saveButton = new JButton();
        saveButton.setIcon(new FlatSVGIcon("ui/icons/menu-saveall_dark.svg"));
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
            cardViewPanel = new JPanel();
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
        new CardCreatorWindow(passwordManagerApp, this);
    }

    // EFFECTS: opens AccountEditorWindow, in order to edit current selected card
    private void editCard() {
        editorWindowOpen = true;
        new CardEditorWindow(passwordManagerApp, selectedAccount, this);
    }

    // EFFECTS: deletes the current selected card
    public void deleteCard() {
        passwordManagerApp.getPasswordManager().deleteSpecificCard(selectedAccount.getTitle());
        accounts = passwordManagerApp.getPasswordManager().getAccounts();

        if (accounts.size() <= 0) {
            selectedAccount = null;
            this.remove(cardViewPanel);
            cardViewPanel = new JPanel();
            this.add(cardViewPanel);
            this.repaint();
            this.setVisible(true);
        } else {
            selectedAccount = accounts.get(0);
            updateCardViewer();
        }
        updateCardList();
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
    }
}
