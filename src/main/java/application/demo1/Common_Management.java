package application.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Common_Management extends Note{

    public void addWord(Word wordObject) {
        if (!(this.contain(wordObject.getWord_target()))) {
        historyList.add(wordObject);
        }

    }

    public boolean contain(String word) {
        boolean flag = false;
        for(Word w: historyList) {
            if((w.getWord_target()).equalsIgnoreCase(word)){
                flag = true;
                break;
            }

        }
        return flag;
    }

    public void delete(String wordTarget){
        for(Word w : historyList){
            if(wordTarget.equalsIgnoreCase(w.getWord_target())){
                historyList.remove(w);
                break;
            }
        }
    }

    public String searchInNote(String wordTarget) {
        Word temp = new Word();
        String answer = "";
        for(Word w : historyList){
            if(wordTarget.equalsIgnoreCase(w.getWord_target())){
                temp = w;
                break;
            }
        }
        if(temp != null) {
            answer = temp.getWord_explain();
        }
        if(answer != null) return answer;
        else return "Không tim thay đã có lỗi";
    }

    public void update(String word, String explain) {
        for(Word w : historyList) {
            if (word.equalsIgnoreCase(w.getWord_target())) {
                w.setWord_explain(explain);
                break;
            }
        }
    }

}
