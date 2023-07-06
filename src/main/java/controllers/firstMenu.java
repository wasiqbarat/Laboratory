package controllers;

import classes.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class firstMenu extends Controller{

    @FXML
    private Button login;

    @FXML
    private Label status;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException {
        String usernameString = username.getText();
        String passwordString = password.getText();

        if (usernameString.isEmpty()) {
            username.setText("Username required!");
            username.requestFocus();
            username.selectAll();
            return;
        }
        if (passwordString.isEmpty()) {
            password.requestFocus();
            password.selectAll();
            return;
        }

        boolean authenticUser = labsSystem.authentication(usernameString, passwordString);

        if (authenticUser) {
            setCurrentUserName(usernameString);
            setCurrentPassword(passwordString);

            labsSystem.log(usernameString, passwordString);

            gotoMainMenu(event);
        }
        else {
            status.setText("Invalid username or email!");
        }

    }

    public void RegisterButtonPressed(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/controllers/RegisterMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

}
