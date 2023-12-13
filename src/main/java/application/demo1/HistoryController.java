package application.demo1;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController extends Controller implements Initializable  {
    @FXML
    private ListView<String> listViewNote;
    @FXML
    private TextArea textAreaNote;
    private MyNote historyWords = AppContent.getHistory();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (listViewNote != null) {
            if (historyWords != null) {
                listViewNote.setItems(historyWords.showWordTarget());
            }

        }
        textAreaNote.setEditable(false);


    }

    public void handleMouseClickResultWord(){
        if (listViewNote != null) {
            String selectWord = listViewNote.getSelectionModel().getSelectedItem();
            if (selectWord != null) {
                String answer = selectWord + "\n" + historyWords.searchInNote(selectWord);

                textAreaNote.setText(answer);
                textAreaNote.setVisible(true);
                textAreaNote.setEditable(false);
                textAreaNote.setWrapText(true);

            }
        }

    }

    @FXML
    public void handleSoundButton() {
        if(listViewNote.getSelectionModel().getSelectedItem() != null) {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            if (voice != null) {
                voice.allocate();
                voice.speak(listViewNote.getSelectionModel().getSelectedItem());
                voice.deallocate();
            } else throw new IllegalStateException("Cannot find voice: kevin16");
        } else {
                showAlertInformation("sound", "Hãy chọn từ để đọc!");
        }
    }

    @FXML
    private void handleDeleteButton() {
        if(listViewNote.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You're about to delete a word!");
            alert.setContentText("Do you want to delete this word?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                String word = listViewNote.getSelectionModel().getSelectedItem();
                historyWords.delete(word);
                listViewNote.setItems(historyWords.showWordTarget());
                textAreaNote.clear();
            }
        } else {
            showAlertInformation("delete", "Hãy chọn từ để xóa!");
        }
    }


}
