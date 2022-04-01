package ui.windows.panels;

import model.AccountCard;
import ui.windows.MainWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

// represents the panel in the main window that holds the list of all the account cards
public class CardListPanel extends JPanel implements ActionListener, MouseListener {
    private static final int BUTTON_HEIGHT = 60;

    private Collection<AccountCard> accounts;
    private final HashMap<AbstractButton, AccountCard> buttonAccountCardHashMap;
    private final MainWindow mainWindow;
    private final ButtonGroup group;

    private JPanel cardList;
    private JScrollPane scrollPane;

    // MODIFIES: this
    // EFFECTS: creates a cardListPanel with given accounts and mainWindow
    public CardListPanel(List<AccountCard> accounts, MainWindow mainWindow) {
        this.accounts = sortAlphabetically(accounts);
        this.mainWindow = mainWindow;
        group = new ButtonGroup();
        buttonAccountCardHashMap = new HashMap<>();
        setupMainPanel();
    }

    // EFFECTS: returns the given list of account cards sorted alphabetically
    public Collection<AccountCard> sortAlphabetically(List<AccountCard> accounts) {
        Collection<AccountCard> cards = new TreeSet<>(new Comparator<AccountCard>() {
            @Override
            public int compare(AccountCard o1, AccountCard o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        cards.addAll(accounts);
        return cards;
    }

    // EFFECTS: returns the given list of account cards sorted by date added
    public Collection<AccountCard> sortRecent(List<AccountCard> accounts) {
        Collection<AccountCard> cards = new TreeSet<AccountCard>(new Comparator<AccountCard>() {
            @Override
            public int compare(AccountCard o1, AccountCard o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        cards.addAll(accounts);
        return cards;
    }

    // MODIFIES: this
    // EFFECTS: sets up the main panel with all necessary attributes and components
    private void setupMainPanel() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBorder(new MatteBorder(0,0,0,2,new Color(56, 88, 126)));
        this.addMouseListener(this);
        setupScrollPanel();
        this.setSize(300, 700);
        this.setVisible(true);
    }

    // MODIFIES: cardList, scrollPane
    // EFFECTS: sets up cardList and scrollPane, with all necessary components and attributes
    private void setupScrollPanel() {
        cardList = new JPanel();
        cardList.setLayout(new BoxLayout(cardList, BoxLayout.Y_AXIS));
        cardList.setBorder(new MatteBorder(0, 0, 0, 0, new Color(74, 74, 87)));
        cardList.addMouseListener(this);
        addCards();

        scrollPane = new JScrollPane(cardList, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().getView().addMouseListener(this);
        scrollPane.setPreferredSize(new Dimension(250, 632));
        scrollPane.setOpaque(false);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
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
        accounts.add(card);
        cardList.removeAll();
        addCards();
        cardList.revalidate();
        scrollPane.revalidate();
    }

    // EFFECTS: sets up the given buttons necessary attributes
    private void setupButton(JToggleButton button, AccountCard card) {
        button.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(56, 88, 126);
            }
        });
        button.setText(card.getTitle());
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 15));
        button.setIcon(new ImageIcon("./images/icons/web_icon.png"));
        button.setIconTextGap(10);
        button.setMaximumSize(new Dimension(250, BUTTON_HEIGHT));
        button.setPreferredSize(new Dimension(250, BUTTON_HEIGHT));
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFocusable(false);
        button.addActionListener(this);
        button.putClientProperty("JButton.buttonType", "borderless");
        button.addMouseListener(this);
        buttonAccountCardHashMap.put(button, card);
        group.add(button);
    }

    // EFFECTS: sets accounts to given accounts
    public void setAccounts(Collection<AccountCard> accounts) {
        this.accounts = accounts;
    }

    // EFFECTS: return cardList
    public JPanel getCardList() {
        return cardList;
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // not used
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // not used
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // not used
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    }
}