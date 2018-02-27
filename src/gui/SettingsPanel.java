package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SettingsPanel {
    private final JPanel panel;
    private final JButton enterNameButton;
    private final JButton enterTimeButton;
    private final JButton startButton;

    SettingsPanel() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        enterNameButton = new JButton("Enter name");
        enterNameButton.setFocusable(false);

        enterTimeButton = new JButton("Enter time");
        enterTimeButton.setFocusable(false);

        startButton = new JButton("Start");
        startButton.setFocusable(false);

        panel.add(enterNameButton);
        panel.add(enterTimeButton);
        panel.add(startButton);
    }


    public JPanel getPanel() {
        return panel;
    }

    public JButton getEnterNameButton() {
        return enterNameButton;
    }

    public JButton getEnterTimeButton() {
        return enterTimeButton;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
