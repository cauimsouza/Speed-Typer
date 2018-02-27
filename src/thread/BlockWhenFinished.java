package thread;

import gui.Gui;

import java.util.concurrent.CountDownLatch;

public class BlockWhenFinished extends Thread{
    private final Gui gui;
    private final CountDownLatch timeIsOver;
    private final TextInputListener textInputListener;

    public BlockWhenFinished(Gui gui, CountDownLatch timeIsOver, TextInputListener textInputListener) {
        this.gui = gui;
        this.timeIsOver = timeIsOver;
        this.textInputListener = textInputListener;
    }

    @Override
    public void run() {
        try{
            timeIsOver.await();
            gui.blockInput();
            textInputListener.addLastWord();
        }
        catch(InterruptedException ex) {
            timeIsOver.countDown();
        }
    }
}
