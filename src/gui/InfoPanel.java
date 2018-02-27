package gui;

import javax.swing.*;  
import java.awt.*;

class InfoPanel {
    private final JPanel panel;
    private final JLabel infoLabel;

    InfoPanel(String title, String info) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        panel.add(titleLabel, BorderLayout.LINE_START);

        infoLabel = new JLabel(info);
        panel.add(infoLabel, BorderLayout.LINE_END);
    }

    public JPanel getPanel() { return panel; }

    public JLabel getInfoLabel() { return infoLabel; }
}