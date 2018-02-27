package gui;

import javax.swing.*;  
import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class TextPanel{
    private final JPanel panel;
    final JTextArea textArea;

    TextPanel(String title) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JLabel titleLabel = new JLabel(title);
        
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(titleLabel, BorderLayout.CENTER);
        panel.add(upperPanel);
                
        textArea = new JTextArea(6, 25);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFocusable(true);
        textArea.setOpaque(false);

        JScrollPane scrollPane =  new JScrollPane(textArea,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
            );
        
        panel.add(scrollPane);
    }

    public JPanel getPanel() { return panel; }

    public JTextArea getTextArea() { return textArea; }
}