package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class firstMenu extends Controller implements Initializable {
    @FXML
    private AnchorPane pictureAnchorPane;

    @FXML
    private AnchorPane buttonsAnchorPane;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label status;


    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException {
        String usernameString = username.getText();
        String passwordString = password.getText();

        if (usernameString.isEmpty()) {
            username.requestFocus();
            return;
        }
        if (passwordString.isEmpty()) {
            password.requestFocus();
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
            status.setText("Invalid username or password!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
