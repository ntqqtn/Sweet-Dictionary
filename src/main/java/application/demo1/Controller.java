package application.demo1;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public abstract class Controller {
    public abstract void handleMouseClickResultWord();
    public abstract void handleSoundButton();
    public void showAlertInformation(String title, String content) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle(title);
        alert1.setHeaderText(null);
        alert1.setContentText(content);
        alert1.showAndWait();
    }

    }
