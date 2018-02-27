package thread;

import gui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterTimeListener implements ActionListener {
    private int time;
    private final Gui gui;

    public EnterTimeListener(Gui gui) {
        this.time = 0;
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String input = JOptionPane.showInputDialog("Please enter the duration in seconds (max. 600 seconds)");

            int newTime = Integer.parseInt(input);

            if(newTime <= 0 || newTime > 10 * 60)  throw new IllegalArgumentException("Duration must be positive and no greater than 600 seconds!");

            time = newTime;
            if(gui.getCountdownLabel().getText().equals(""))
                gui.getCountdownLabel().setText(String.format("%d:%02d", time / 60, time % 60));
            else
                gui.blockInput();
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Duration must be an integer (e.g. 60, 120, 300)!", "Invalid duration", JOptionPane.ERROR_MESSAGE);
            time = 0;
        }
        catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid duration", JOptionPane.ERROR_MESSAGE);
            time = 0;
        }

    }

    public int getTime() {
        return time;
    }
}
