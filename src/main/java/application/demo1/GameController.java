package application.demo1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private Dictionary dictionary = AppContent.getDictionary();
    private Game game = new Game();
    Word keyWord;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label labelWord;

    @FXML
    Label labelRight;
     @FXML
     Label labelWrong;


    private List<Word> fourWordRandom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }

    @FXML
    public void handleClickLabel1() {
        labelWrong.setVisible(false);
        labelWrong.setVisible(false);
        if (label1.getText() == keyWord.getWord_explain()) {
            labelRight.setVisible(true);
            labelWrong.setVisible(false);


        } else {
            labelWrong.setVisible(true);
        }
    }

    @FXML
    public void handleClickLabel2() {
        labelWrong.setVisible(false);
        labelWrong.setVisible(false);
        if (label2.getText() == keyWord.getWord_explain()) {
            labelRight.setVisible(true);
            labelWrong.setVisible(false);


        } else {
            labelWrong.setVisible(true);
        }
    }
    @FXML
    public void handleClickLabel3() {
        labelWrong.setVisible(false);
        labelWrong.setVisible(false);
        if (label3.getText() == keyWord.getWord_explain()) {
            labelRight.setVisible(true);
            labelWrong.setVisible(false);

        } else {
            labelWrong.setVisible(true);
        }
    }
    @FXML
    public void handleClickLabel4() {
        labelWrong.setVisible(false);
        labelWrong.setVisible(false);
        if (label4.getText() == keyWord.getWord_explain()) {
            labelRight.setVisible(true);
            labelWrong.setVisible(false);
        } else {
            labelWrong.setVisible(true);
        }
    }

    @FXML
    public void handleResetButton() {
        reset();
    }

    public void reset() {
        fourWordRandom = game.getFourElementFirstList(dictionary.getWordObjectList());
        keyWord = game.getKeyWord(fourWordRandom);
        labelWord.setText(keyWord.getWord_target());
        label1.setText(fourWordRandom.get(0).getWord_explain());
        label2.setText(fourWordRandom.get(1).getWord_explain());
        label3.setText(fourWordRandom.get(2).getWord_explain());
        label4.setText(fourWordRandom.get(3).getWord_explain());

        labelRight.setVisible(false);
        labelWrong.setVisible(false);
    }

}
