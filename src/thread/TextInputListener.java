package thread;

import javax.swing.text.*;
import javax.swing.event.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;

public class TextInputListener implements DocumentListener {
    private final String dictionaryFile = "../dictionary/american-english";

    private HashSet<String> dictionary;
    private final BlockingQueue<Integer> pointsQueue;
    private final BlockingQueue<String> wrongWordsQueue;
    private final BlockingQueue<Boolean> typeQueue;
    private final Stack<String> wordsList;
    private final Stack<Integer> posList;
    private String newWord;
    private int textLength;

    public TextInputListener(BlockingQueue<Integer> queue, BlockingQueue<String> wrongWrodsQueue, BlockingQueue<Boolean> typeQueue){
        super();
        this.dictionary = new HashSet<>();
        this.pointsQueue = queue;
        this.wrongWordsQueue = wrongWrodsQueue;
        this.typeQueue = typeQueue;
        this.wordsList = new Stack<>();
        this.posList = new Stack<>();
        this.newWord = "";
        this.textLength = 0;

        createDictionary();
    }

    public void insertUpdate (DocumentEvent e) {
        Document doc = e.getDocument();

        try {
            String newCharacter = doc.getText(textLength, 1);

            if(!Character.isLetter(newCharacter.charAt(0))) {
                posList.push(textLength);
                wordsList.push(newWord);

                int points = newWord.length();
                if(!dictionary.contains(newWord.toLowerCase())) {
                    points *= -1;
			
                    typeQueue.add(true);
                    wrongWordsQueue.add(newWord);
                }

                pointsQueue.add(points);
                newWord = "";
            }
            else {
                newWord += newCharacter;
            }

            textLength++;
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    public void removeUpdate(DocumentEvent e) {
        if(textLength > 0 ) textLength--;


        if(!posList.empty() && textLength == posList.peek()) {
            posList.pop();
            newWord = wordsList.pop();

            int points = - newWord.length();
            if(!dictionary.contains(newWord.toLowerCase())) {
                points *= -1;

                wrongWordsQueue.add(newWord);
                typeQueue.add(false);
            }

            pointsQueue.add(points);
        }
        else if(textLength >= 0) {
            if(newWord.length() > 0)
                newWord = newWord.substring(0, newWord.length() - 1);
            else
                newWord = "";
        }
    }

    public void changedUpdate(DocumentEvent e) {
        //Plain text components do not fire these events
    }

    public void reset() {
        wordsList.clear();
        posList.clear();
        newWord = "";
        textLength = 0;
    }

    private void createDictionary() {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));

            String word = reader.readLine();
            while(word != null) {
                dictionary.add(word);
                word = reader.readLine();
            }

            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addLastWord() {
        if(newWord.length() > 0) {
            int points = newWord.length();
            if(!dictionary.contains(newWord.toLowerCase())) {
                points *= -1;

                typeQueue.add(true);
                wrongWordsQueue.add(newWord);
            }

            pointsQueue.add(points);
        }
    }
}
