package application.demo1;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends Controller implements Initializable {
    @FXML
    private TextArea definitionText;
    @FXML
    private TextField searchText;
    @FXML
    private ListView<String> listResults;
    private MyNote myNote = AppContent.getHistory();
    private  Dictionary dictionary;

    private ObservableList<String> listResultToPrint = FXCollections.observableArrayList();


    /** tạo từ điển từ luồng riêng */
    public SearchController() {
        dictionary = AppContent.getDictionary();

        Platform.runLater(() -> {
                listResults.setItems(dictionary.getWordsList());
            });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchText.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (searchText.getText().isEmpty()) {
                    System.out.println("ListView is empty");
                    listResults.getItems().clear();
                    definitionText.clear();
                    definitionText.setEditable(false);
                    listResults.setItems(dictionary.getWordsList());
                } else {
                    try{
                    handleKeyTyped();
                    }
                    catch (NullPointerException e){
                        System.out.println("null ex");
                    }
                }
            }
        });

        definitionText.setEditable(false);


    }
    public void handleKeyTyped() {
        listResultToPrint.clear();
        String keyText = searchText.getText().trim();
        listResultToPrint = dictionary.predictList(keyText);
        System.out.println("từ tìm kiếm " + keyText);
        System.out.println(listResultToPrint);
        if(dictionary.ifPredictNull) {
            System.out.println("khong co trong tu dien");
        }
            listResults.setItems(listResultToPrint);
            definitionText.setEditable(false);
    }

    @FXML
    public void handleMouseClickResultWord(){
        if (listResults != null) {
            String selectWord = listResults.getSelectionModel().getSelectedItem();
        if (selectWord != null) {
            String answer = selectWord + "\n" + dictionary.search(selectWord);

            Word tmpWord = new Word(selectWord,dictionary.search(selectWord));
            myNote.addWord(tmpWord);

            definitionText.setText(answer);
            definitionText.setVisible(true);
            definitionText.setEditable(false);
            definitionText.setWrapText(true);
            AppContent.setHistory(myNote);

        }
        else {
            definitionText.setVisible(true);
            definitionText.setEditable(false);
             }
        }

    }

    @FXML
    public void handleSoundButton() {
        if(listResults.getSelectionModel().getSelectedItem() != null) {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Voice voice = VoiceManager.getInstance().getVoice("kevin16");
            if (voice != null) {
                voice.allocate();
                voice.speak(listResults.getSelectionModel().getSelectedItem());
                voice.deallocate();
            } else throw new IllegalStateException("Cannot find voice: kevin16");
        } else {
            showAlertInformation("sound", "Hãy chọn từ để đọc!");
        }
    }




}
