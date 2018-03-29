package gui;

import javax.swing.*;

public class Gui {
    private final SettingsPanel settingsPanel;
    private final InfoPanel bestScorePanel;
    private final InfoPanel namePanel;
    private final InfoPanel countdownPanel;
    private final InfoPanel scorePanel;
    private final TypingPanel textPanel;
    private final TextPanel wrongPanel;
    private final JFrame frame;

    public Gui() {
        this.settingsPanel = new SettingsPanel();
        this.bestScorePanel = new InfoPanel("All time best score", "0");
        this.namePanel = new InfoPanel("Name", "");
        this.countdownPanel = new InfoPanel("Time remaining", "");
        this.scorePanel = new InfoPanel("Score", "0");
        this.textPanel = new TypingPanel("Digit here");
        this.wrongPanel = new TextPanel("Wrong words");

        //JFrame.setDefaultLookAndFeelDecorated(true);
        this.frame = new JFrame("Speed Typer");
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout();

        frame.pack();
        frame.setResizable(false);

        frame.setVisible(true);
    }

    private void setLayout(){
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        //String userName = JOptionPane.showInputDialog(frame, "Please enter your name");
        //System.out.println(userName);

        listPane.add(settingsPanel.getPanel());

        listPane.add(bestScorePanel.getPanel());

        listPane.add(namePanel.getPanel());

        listPane.add(countdownPanel.getPanel());

        listPane.add(scorePanel.getPanel());

        listPane.add(textPanel.getPanel());

        listPane.add(wrongPanel.getPanel());


        frame.setContentPane(listPane);
    }
    
    public JLabel getScoreInfoLabel() { return scorePanel.getInfoLabel(); }

    public JLabel getBestScoreLabel() { return bestScorePanel.getInfoLabel(); }

    public JTextArea getInputTextArea() { return textPanel.getTextArea(); }

    public JLabel getCountdownLabel() { return countdownPanel.getInfoLabel(); }

    public JLabel getNameLabel() { return namePanel.getInfoLabel(); }

    public JTextArea getWrongWordsLabel() { return wrongPanel.getTextArea(); }

    public void blockInput() {
        textPanel.getTextArea().setEditable(false);
        textPanel.getTextArea().setOpaque(false);
        textPanel.getTextArea().setFocusable(false);
    }

    public JButton getEnterNameButton() {
        return settingsPanel.getEnterNameButton();
    }

    public JButton getEnterTimeButton() {
        return settingsPanel.getEnterTimeButton();
    }

    public JButton getStartButton() {
        return settingsPanel.getStartButton();
    }

    public void restartGui() {
        textPanel.getTextArea().setFocusable(true);
        textPanel.getTextArea().setEditable(true);
        textPanel.getTextArea().setOpaque(true);
        textPanel.getTextArea().setText("");
	textPanel.getTextArea().getCaret().setVisible(true);

        scorePanel.getInfoLabel().setText("0");
    }
}
