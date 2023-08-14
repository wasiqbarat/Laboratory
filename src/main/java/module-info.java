/*module com.example.laboratory {
    requires javafx.controls;
        requires javafx.fxml;
        requires java.base;

    requires org.controlsfx.controls;

    exports controllers;

    opens controllers to javafx.fxml;
}*/
module com.example.laboratory {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    exports controllers;
    exports classes;

    opens classes to javafx.fxml;
    opens controllers to javafx.fxml;
}


