package application.demo1;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class MainGuiController implements Initializable {
    @FXML
    private Button search;
    @FXML
    private AnchorPane content;
    @FXML
    private Label labelTime;

    @FXML
    public void switchSearch(ActionEvent event) {

        loadAnchorPane("SearchGui.fxml");
    }

    @FXML
    public void switchNote(ActionEvent event) {
        loadAnchorPane("NoteGui.fxml");

    }
    @FXML
    public void switchHistory(ActionEvent event) {
        loadAnchorPane("HistoryGui.fxml");

    }
    @FXML
    public void switchGame(ActionEvent event) {
        loadAnchorPane("GameGui.fxml");

    }


    public void loadAnchorPane(String path){
        try{ AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            content.getChildren().clear();
            content.getChildren().add(anchorPane); }

        catch (IOException e) {
            e.printStackTrace();
            System.out.println("không Load được AnchorPane!!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

         Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Lấy thời gian hiện tại
            Date now = new Date();

            // Định dạng thời gian
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            // Hiển thị thời gian trong Label
             if (labelTime != null) {
                 labelTime.setText("Thời gian hiện tại : " + dateFormat.format(now));
             }       }));

        // Lặp vô hạn
        timeline.setCycleCount(Animation.INDEFINITE);

        // Bắt đầu Timeline
        timeline.play();
        loadAnchorPane("SearchGui.fxml");
    }


}