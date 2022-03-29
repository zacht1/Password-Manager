package ui.windows.popups;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// represents a pop-up dialog box that informs user that they have saved any changes
public class SuccessfulSaveDialogBox {
    private static final String SMALL_LOGO_FILE = "images/blue_logo_small.png";

    private final JDialog dialogBox;
    private JLabel label;
    private JLabel icon;

    // MODIFIES: dialogBox
    // EFFECTS: sets up the saved dialog box and all its necessary attributes and components
    public SuccessfulSaveDialogBox(JFrame frame) {
        setupLabel();
        setupIcon();

        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.schedule(new Runnable() {
            public void run() {
                dialogBox.setVisible(false); //should be invoked on the EDT
                dialogBox.dispose();
            }
        }, 500, TimeUnit.MILLISECONDS);

        dialogBox = new JDialog(frame, true);
        dialogBox.setLayout(null);
        dialogBox.setUndecorated(true);
        dialogBox.setSize(230,120);
        dialogBox.setResizable(false);
        dialogBox.setLocationRelativeTo(frame);
        dialogBox.add(label);
        dialogBox.add(icon);
        dialogBox.setVisible(true);
    }

    // MODIFIES: label
    // EFFECTS: setup all necessary attributes of saved changes label
    private void setupLabel() {
        label = new JLabel("Saved changes", SwingConstants.CENTER);
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
}
