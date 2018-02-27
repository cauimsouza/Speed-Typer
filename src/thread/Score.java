package thread;

import gui.Gui;

import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;

public class Score implements Runnable{
    private long score;
    private long highestScore;
    private final Gui gui;
    private final BlockingQueue<Integer> queue;

    public Score(Gui gui, BlockingQueue<Integer> queue) {
        this.score = 0;
        this.highestScore = 0;
        this.gui = gui;
        this.queue = queue;

        gui.getBestScoreLabel().setText("0");
    } 

    public void run() {
        try{
            while(true) {
                int increment = queue.take();
                score += increment;
                gui.getScoreInfoLabel().setText(Long.toString(score));

                if(score > highestScore) {
                    highestScore = score;
                    gui.getBestScoreLabel().setText(Long.toString(highestScore));
                }
            }
        }
        catch(InterruptedException ex) {
            return;
        }
    }

    public void reset() {
        score = 0;
        gui.getScoreInfoLabel().setText("0");
    }
}