package thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener{
    private final EnterNameListener enterNameListener;
    private final EnterTimeListener enterTimeListener;
    private final Object object;

    public StartListener(EnterNameListener enterNameListener, EnterTimeListener enterTimeListener, Object object) {
        this.enterNameListener = enterNameListener;
        this.enterTimeListener = enterTimeListener;
        this.object = object;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        synchronized (object) {
            if(enterNameListener.getUserName().length() > 0 && enterTimeListener.getTime() > 0) {
                object.notifyAll();
            }
        }
    }
}
