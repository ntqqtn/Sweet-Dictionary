module application.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires freetts;


    opens application.demo1 to javafx.fxml;
    exports application.demo1;
}