package application.demo1;

import java.util.HashMap;

public class TrieNode{
    protected HashMap<Character, TrieNode> children = new HashMap<>();
    protected boolean isEndOfWord = false;
    protected Word wordObject;

    public void setWordObject(String word_target, String word_explain) {
        this.wordObject = new Word(word_target, word_explain);
    }

    public Word getWordObject() {
        return wordObject;
    }

    public TrieNode(){
        isEndOfWord = false;
    }
}
