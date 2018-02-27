package thread;

import gui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterNameListener implements ActionListener {
    private String userName;
    private final Gui gui;

    public EnterNameListener(Gui gui) {
        this.userName = "";
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String newUserName = JOptionPane.showInputDialog("Please enter your name");

        if(newUserName == null || newUserName.length() == 0) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Invalid name", JOptionPane.ERROR_MESSAGE);
        }
        else if(newUserName.length() > 15) {
            JOptionPane.showMessageDialog(null, "Name cannot be more than 15 characters long!", "Invalid name", JOptionPane.ERROR_MESSAGE);
        }
        else {
            if(!userName.equals(""))    gui.blockInput();
            userName = newUserName;
            gui.getNameLabel().setText(userName);
        }
    }

    public String getUserName() {
        return userName;
    }
}
