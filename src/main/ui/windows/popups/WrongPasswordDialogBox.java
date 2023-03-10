package ui.windows.popups;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// represents a pop-up dialog box that informs user that they have entered the wrong password
public class WrongPasswordDialogBox implements ActionListener {
    private static final String SMALL_LOGO_FILE = "images/blue_logo_small.png";

    private final JDialog dialogBox;
    private JLabel label;
    private JLabel icon;
    private JButton button;

    // MODIFIES: dialogBox
    // EFFECTS: sets up the wrong password dialog box and all its necessary attributes and components
    public WrongPasswordDialogBox(String text, JFrame frame) {
        setupButton();
        setupLabel(text);
        setupIcon();

        dialogBox = new JDialog(frame, true);
        dialogBox.setLayout(null);
        dialogBox.setUndecorated(true);
        dialogBox.setSize(230,150);
        dialogBox.setResizable(false);
        dialogBox.setLocationRelativeTo(frame);
        dialogBox.add(button);
        dialogBox.add(label);
        dialogBox.add(icon);
        dialogBox.getRootPane().setDefaultButton(button);
        dialogBox.setVisible(true);
    }

    // MODIFIES: button
    // EFFECTS: setup all necessary attributes of the okay button
    private void setupButton() {
        button = new JButton("Ok");
        button.setBounds(15,100,200,30);
        button.addActionListener(this);
        button.setFocusable(false);
    }

    // MODIFIES: label
    // EFFECTS: setup all necessary attributes of the wrong password label
    private void setupLabel(String text) {
        label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(62, 72, 106, 30);
        label.setFont(new Font("Helvetica Neue", Font.BOLD, 13));
    }

    // MODIFIES: icon, img
    // EFFECTS: setup all necessary attributes of the icon label
    private void setupIcon() {
        icon = new JLabel();
        ImageIcon img = new ImageIcon(SMALL_LOGO_FILE);
        icon.setIcon(img);
        icon.setBounds(75,-5,100,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            dialogBox.dispatchEvent(new WindowEvent(dialogBox, WindowEvent.WINDOW_CLOSING));
        }
    }
}
