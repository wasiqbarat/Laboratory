module com.example.laboratory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports controllers;
    opens controllers to javafx.fxml;

}
