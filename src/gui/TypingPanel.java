package gui;

class TypingPanel extends TextPanel{
    TypingPanel(String title) {
        super(title);
        textArea.setEditable(true);
        textArea.setOpaque(true);
    }
}