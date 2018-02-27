package thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import javax.swing.JLabel;

public class Stopwatch implements Runnable{
    private int interval;
    private final JLabel timerLabel;
    private Timer timer;
    private final CountDownLatch doneSignal;
    private final EnterTimeListener enterTimeListener;

    public Stopwatch(JLabel timerLabel, EnterTimeListener enterTimeListener, CountDownLatch doneSignal) {
        this.timerLabel = timerLabel;
        this.doneSignal = doneSignal;
        this.enterTimeListener = enterTimeListener;
    }

    public void run() {
        int delay = 0;
        int period = 1000;
        interval = enterTimeListener.getTime();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                int minutes = setInterval() / 60;
                int seconds = interval % 60;
                timerLabel.setText(String.format("%d:%02d", minutes, seconds));
            }
        }, delay, period);
    }

    private int setInterval() {
        if (interval == 1) {
            timer.cancel();
            doneSignal.countDown();
        }
        return --interval;
    }

    public void interruptCounter() {
        timer.cancel();
    }
}