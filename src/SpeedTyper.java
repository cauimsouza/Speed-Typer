import gui.Gui;
import thread.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class SpeedTyper {

    public static void main(String[] args){
        Gui gui = new Gui();

        BlockingQueue<Integer> pointsQueue = new LinkedBlockingQueue<>();
        BlockingQueue<String> wrongWordsQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Boolean> typeQueue = new LinkedBlockingQueue<>();

        Score score = new Score(gui, pointsQueue);
        WrongWordsManager wrongWordsManager = new WrongWordsManager(gui.getWrongWordsLabel(), wrongWordsQueue, typeQueue);
        TextInputListener textInputListener = new TextInputListener(pointsQueue, wrongWordsQueue, typeQueue);
        gui.getInputTextArea().getDocument().addDocumentListener(textInputListener);

        EnterNameListener enterNameListener = new EnterNameListener(gui);
        gui.getEnterNameButton().addActionListener(enterNameListener);

        EnterTimeListener enterTimeListener = new EnterTimeListener(gui);
        gui.getEnterTimeButton().addActionListener(enterTimeListener);

        Object object = new Object();
        StartListener startListener = new StartListener(enterNameListener, enterTimeListener, object);
        gui.getStartButton().addActionListener(startListener);

        Stopwatch stopwatch = null;
        Thread threadScore = null;
        Thread blockWhenFinished = null;
        Thread wrongWordsManagerThread = null;
        while(true) {
            synchronized (object) {
                try {
                    object.wait();

                    if(stopwatch != null) stopwatch.interruptCounter();
                    if(threadScore != null && !threadScore.isInterrupted()) threadScore.interrupt();
                    if(blockWhenFinished != null && !blockWhenFinished.isInterrupted()) blockWhenFinished.interrupt();
                    if(wrongWordsManagerThread != null && !wrongWordsManagerThread.isInterrupted())  wrongWordsManagerThread.interrupt();

                    pointsQueue.clear();
                    wrongWordsQueue.clear();
                    textInputListener.reset();
                    wrongWordsManager.reset();

                    wrongWordsManagerThread = new Thread(wrongWordsManager);
                    wrongWordsManagerThread.start();

                    CountDownLatch timeIsOver = new CountDownLatch(1);
                    stopwatch = new Stopwatch(gui.getCountdownLabel(), enterTimeListener, timeIsOver);
                    new Thread(stopwatch).start();

                    score.reset();
                    threadScore = new Thread(score);
                    threadScore.start();

                    gui.restartGui();
                    blockWhenFinished = new BlockWhenFinished(gui, timeIsOver, textInputListener);
                    blockWhenFinished.start();

                } catch (InterruptedException ex) { }
            }
        }
    }
}