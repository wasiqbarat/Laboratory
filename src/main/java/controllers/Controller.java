package controllers;

import classes.LabsSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller {
    protected LabsSystem labsSystem;

    private static String currentUserName = "";
    private static String currentPassword = "";

    public Controller() {
        labsSystem = LabsSystem.getInstance();
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        Controller.currentUserName = currentUserName;
    }

    public LabsSystem getLabsSystem() {
        return labsSystem;
    }

    public void setLabsSystem(LabsSystem labsSystem) {
        this.labsSystem = labsSystem;
    }

    public static String getCurrentPassword() {
        return currentPassword;
    }

    public static void setCurrentPassword(String currentPassword) {
        Controller.currentPassword = currentPassword;
    }

    public void gotoMainMenu(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/mainMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setWidth(900);
        stage.setHeight(550);
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();
    }
}

