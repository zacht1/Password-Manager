package ui.windows.popups;

import ui.windows.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

// represents a pop-up dialog box that confirms user wants to delete selected card
public class DeleteConfirmationDialogBox implements ActionListener {
    private static final String SMALL_LOGO_FILE = "images/blue_logo_small.png";

    private JLabel label;
    private JLabel icon;
    private JButton okButton;
    private JButton cancelButton;

    private final MainWindow mainWindow;
    private final JDialog dialogBox;

    // MODIFIES: this, dialogBox
    // EFFECTS: instantiates DeleteConfirmationDialogBox with given mainWindow, and sets up the dialog box
    public DeleteConfirmationDialogBox(MainWindow frame) {
        this.mainWindow = frame;
        setupButtons();
        setupLabel();
        setupIcon();

        dialogBox = new JDialog(frame, true);
        dialogBox.setLayout(null);
        dialogBox.setUndecorated(true);
        dialogBox.setSize(230,150);
        dialogBox.setResizable(false);
        dialogBox.setLocationRelativeTo(frame);
        dialogBox.add(okButton);
        dialogBox.add(cancelButton);
        dialogBox.add(label);
        dialogBox.add(icon);
        dialogBox.getRootPane().setDefaultButton(okButton);
        dialogBox.setVisible(true);
    }

    // MODIFIES: okButton
    // EFFECTS: setup all necessary attributes of the okay and cancel button
    private void setupButtons() {
        okButton = new JButton("Ok");
        okButton.setBounds(120,100,95,30);
        okButton.addActionListener(this);
        okButton.setFocusable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(15,100,95,30);
        cancelButton.addActionListener(this);
        cancelButton.setFocusable(false);
    }

    // MODIFIES: label
    // EFFECTS: setup all necessary attributes of the label
    private void setupLabel() {
        label = new JLabel("Delete card?", SwingConstants.CENTER);
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
        if (e.getSource() == okButton) {
            mainWindow.deleteCard();
            dialogBox.dispatchEvent(new WindowEvent(dialogBox, WindowEvent.WINDOW_CLOSING));
        }
        if (e.getSource() == cancelButton) {
            dialogBox.dispatchEvent(new WindowEvent(dialogBox, WindowEvent.WINDOW_CLOSING));
        }
    }
}