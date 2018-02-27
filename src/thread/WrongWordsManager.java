package thread;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.concurrent.BlockingQueue;

public class WrongWordsManager implements Runnable {
    private final JTextArea wrongWordsLabel;
    private final BlockingQueue<String> wordsQueue;
    private final BlockingQueue<Boolean> typeQueue;

    public WrongWordsManager(JTextArea wrongWordsLabel, BlockingQueue<String> wordsQueue, BlockingQueue<Boolean> typeQueue) {
        this.wrongWordsLabel = wrongWordsLabel;
        this.wordsQueue = wordsQueue;
        this.typeQueue = typeQueue;

        wrongWordsLabel.setText("");
    }

    @Override
    public void run() {
        try{
            while (true) {
                boolean type = typeQueue.take();

                String newWord = wordsQueue.take();
                int textLength = wrongWordsLabel.getText().length();
                if (type) {
                    if(textLength > 0 && newWord.length() > 0) wrongWordsLabel.append(" ");

                    wrongWordsLabel.append(newWord);
                }
                else if (newWord.length() == textLength) {
                    wrongWordsLabel.setText("");
                }
                else {
                    if(newWord.length() > 0)
                        wrongWordsLabel.setText(wrongWordsLabel.getText(0, textLength - 1 - newWord.length()));
                }
            }
        }
        catch (InterruptedException e) {
            // do nothing
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        wrongWordsLabel.setText("");
    }
}
