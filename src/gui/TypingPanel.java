package gui;

class TypingPanel extends TextPanel{
    TypingPanel(String title) {
        super(title);
        textArea.setEditable(false);
        textArea.setOpaque(false);
    }
}
