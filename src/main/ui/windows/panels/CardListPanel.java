package ui.windows.panels;

import model.AccountCard;
import ui.windows.MainWindow;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

// represents the panel in the main window that holds the list of all the account cards
public class CardListPanel extends JPanel implements ActionListener {
    private static final int BUTTON_HEIGHT = 60;

    private final List<AccountCard> accounts;
    private final HashMap<AbstractButton, AccountCard> buttonAccountCardHashMap;
    private final MainWindow mainWindow;
    private final ButtonGroup group;

    private JPanel cardList;
    private JScrollPane scrollPane;

    // MODIFIES: this
    // EFFECTS: creates a cardListPanel with given accounts and mainWindow
    public CardListPanel(List<AccountCard> accounts, MainWindow mainWindow) {
        this.accounts = accounts;
        this.mainWindow = mainWindow;
        group = new ButtonGroup();
        buttonAccountCardHashMap = new HashMap<>();
        setupMainPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets up the main panel with all necessary attributes and components
    private void setupMainPanel() {
        this.setLayout(new FlowLayout());
        this.setBorder(new MatteBorder(0,0,0,2,new Color(56, 88, 126)));
        setupScrollPanel();
        this.setSize(300, 700);
        this.setVisible(true);
    }

    // MODIFIES: cardList, scrollPane
    // EFFECTS: sets up cardList and scrollPane, with all necessary components and attributes
    private void setupScrollPanel() {
        cardList = new JPanel();
        cardList.setLayout(new BoxLayout(cardList, BoxLayout.Y_AXIS));
        cardList.setBorder(new MatteBorder(0, 0, 0, 1, new Color(74, 74, 87)));
        addCards();

        scrollPane = new JScrollPane(cardList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(250, 632));
        this.add(scrollPane);
    }

    // MODIFIES: cardList
    // EFFECTS: adds multiple cards to the card list panel
    public void addCards() {
        for (AccountCard ac : accounts) {
            JToggleButton button = new JToggleButton();
            setupButton(button, ac);
            cardList.add(button);
        }
    }

    // MODIFIES: cardList, scrollPane
    // EFFECTS: adds a card to the card list panel
    public void addCard(AccountCard card) {
        JToggleButton button = new JToggleButton();
        setupButton(button, card);
        cardList.add(button);
        cardList.revalidate();
        scrollPane.revalidate();
    }

    // EFFECTS: sets up the given buttons necessary attributes
    private void setupButton(JToggleButton button, AccountCard card) {
        button.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setText(card.getTitle());
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        button.setIcon(new ImageIcon("./images/icons/web_icon.png"));
        button.setIconTextGap(10);
        button.setMaximumSize(new Dimension(243, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(243, BUTTON_HEIGHT));
        button.setFocusable(false);
        button.addActionListener(this);
        button.putClientProperty("JButton.buttonType", "borderless");
        buttonAccountCardHashMap.put(button, card);
        group.add(button);
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<AbstractButton> buttons = new ArrayList<>();

        for (Enumeration<AbstractButton> b = group.getElements(); b.hasMoreElements();) {
            buttons.add(b.nextElement());
        }

        //noinspection SuspiciousMethodCalls
        if (buttons.contains(e.getSource())) {
            mainWindow.setSelectedAccount(buttonAccountCardHashMap.get((AbstractButton) e.getSource()));
            mainWindow.updateCardViewer();
        }
    }
}