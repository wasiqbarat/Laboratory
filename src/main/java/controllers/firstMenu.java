package controllers;

import classes.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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

            Log log = new Log(usernameString, passwordString, "login");
            log.setInfo("Login date(تاریخ و زمان ورود):    " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a")));
            labsSystem.addLog(log);

            gotoMainMenu(event);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        else {
            status.setText("Invalid username or password!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
