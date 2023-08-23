package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Die after October 1, 2010
        Calendar expireDate = Calendar.getInstance();;
        // January is 0 (y, m, d)
        expireDate.set(2023, 8, 0);

        // Get current date and compare
        if (Calendar.getInstance().after(expireDate)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Expiration");
            alert.setHeaderText("Your free trial has ended!\n!اشتراک رایگان شما به پایان رسید");
            alert.showAndWait();
            System.exit(0);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("firstMenu.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sohail laboratory");
        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Laboratory\\src\\main\\resources\\assets\\icon1.png"));
        //stage.getIcons().add(new Image("Icons/icon1.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}