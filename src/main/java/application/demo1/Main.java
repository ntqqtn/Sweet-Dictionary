package application.demo1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGui.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root,850,500);;
        String css = Objects.requireNonNull(this.getClass().getResource("style.css")).toExternalForm();
        scene.getStylesheets().add(css);


        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume(); //ngan chan default close action
            logOut(stage);});
    }

    public void logOut(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("close successfully!");
            stage.close();}
    }

    public static void main(String[] args) {
        launch();
    }

}

