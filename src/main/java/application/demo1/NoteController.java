package application.demo1;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class NoteController extends Controller implements Initializable {

    @FXML
    private ListView<String> listViewMyNote;

    @FXML
    private TextArea wordArea;
    @FXML
    private TextArea explainArea;

    @FXML
    private TextField addWordField;

    @FXML
    private TextArea addExplainArea;

    @FXML
    Button updateButton2;

    @FXML
    Button addButton;

    @FXML
    Pane successPane;
    private MyNote note = AppContent.getMyNoteContent();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(listViewMyNote != null) {
            if (note != null) {
                listViewMyNote.setItems(note.showWordTarget());
            }
        }

        if (addWordField.getText().isEmpty() || addExplainArea.getText().isEmpty()) {
            addButton.setDisable(true);
        }

        addExplainArea.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (addWordField.getText().isEmpty() || addExplainArea.getText().isEmpty()) {
                    addButton.setDisable(true);
                } else {addButton.setDisable(false);}
            }
        });

        addWordField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (addWordField.getText().isEmpty() || addExplainArea.getText().isEmpty()) {
                    addButton.setDisable(true);
                } else {addButton.setDisable(false);}
            }
        });

        successPane.setVisible(false);

        explainArea.setEditable(false);
        wordArea.setEditable(false);
        updateButton2.setVisible(false);
        successPane.setVisible(false);
    }
    @Override
    @FXML
    public void handleMouseClickResultWord() {
        if (listViewMyNote != null) {
            String selectWord = listViewMyNote.getSelectionModel().getSelectedItem();
            if (selectWord != null) {
                String answer =  note.searchInNote(selectWord);

                wordArea.setText(selectWord);
                explainArea.setText(answer);
                explainArea.setVisible(true);
                explainArea.setEditable(false);
                wordArea.setVisible(true);
                wordArea.setEditable(false);
                wordArea.setWrapText(true);
                explainArea.setWrapText(true);

            }
        }
        successPane.setVisible(false);

    }

    @Override
    @FXML
    public void handleSoundButton() {
        if(listViewMyNote.getSelectionModel().getSelectedItem() != null) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if(voice != null) {
            voice.allocate();
            voice.speak(listViewMyNote.getSelectionModel().getSelectedItem());
            voice.deallocate();
        } else throw new IllegalStateException("Cannot find voice: kevin16");
        successPane.setVisible(false);
        } else {
            showAlertInformation("sound", "Hãy chọn từ để đọc!");
        }



    }

    @FXML
    public void handleClickFirstUpdateButton() {
        if(listViewMyNote.getSelectionModel().getSelectedItem() != null) {
        explainArea.setEditable(true);
        updateButton2.setVisible(true);
        successPane.setVisible(false);
        } else {
            showAlertInformation("update", "Hãy chọn từ để update");
        }

    }

    @FXML
    public void handleClickSecondUpdateButton(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You're about to update a word!");
            alert.setContentText("Do you want to update this word?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                String word = listViewMyNote.getSelectionModel().getSelectedItem();
                String explain = explainArea.getText();
                note.update(word, explain);

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("update");
                alert1.setHeaderText(null);
                alert1.setContentText("bạn đã update thành công");
                alert1.showAndWait();
            }
            updateButton2.setVisible(false);
            explainArea.setEditable(false);
            successPane.setVisible(false);


    }
    @FXML
    public void handleAddButton() {
        String wordTarget = addWordField.getText().trim();
        String explain = addExplainArea.getText().trim();
        Word word =new Word(wordTarget, explain);
        if(note.contain(wordTarget)) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("add");
            alert1.setHeaderText(null);
            alert1.setContentText("từ này đã có trong note");
            alert1.showAndWait();
        } else {
            note.addWord(word);
            successPane.setVisible(true);
            listViewMyNote.setItems(note.showWordTarget());
            addWordField.clear();
            addExplainArea.clear();
        }
    }


    @FXML
    public void handleDeleteButton() {
        if(listViewMyNote.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("You're about to delete a word!");
            alert.setContentText("Do you want to delete this word?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                String word = listViewMyNote.getSelectionModel().getSelectedItem();
                note.delete(word);
                listViewMyNote.setItems(note.showWordTarget());
                explainArea.clear();
                wordArea.clear();
                successPane.setVisible(false);

            }
        } else {
            showAlertInformation("delete", "Hãy chọn từ để xóa!");
        }
    }


}
