package controllers;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileMenu extends Controller implements Initializable {


    @FXML
    private TextField abilities;

    @FXML
    private TextField userName;

    @FXML
    void changePasswordButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/changeUsernameOrPassword.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void previous(ActionEvent event) throws IOException {
        gotoMainMenu(event);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = null;
        for (User user1 : labsSystem.getUsers()) {
            if (getCurrentUserName().equals(user1.getUserName())) {
                user = user1;
            }
        }

        userName.setText(user.getUserName());
        abilities.setText(user.getRole().abilitiesToString());

    }
}
