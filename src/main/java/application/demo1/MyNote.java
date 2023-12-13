package application.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MyNote extends Common_Management{
    public ObservableList<String> showWordTarget() {
        ObservableList<String> wordTarget = FXCollections.observableArrayList();
        for(Word w : historyList) {
            wordTarget.add(w.getWord_target());
        }
        return wordTarget;
    }

}
