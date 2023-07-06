package controllers;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class ChangeUsernameOrPassword extends Controller implements Initializable {

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private TextField oldPassword;

    @FXML
    private Label username;

    @FXML
    private Label status;

    @FXML
    void applyButtonPressed(ActionEvent event) throws IOException {

        if (oldPassword.getText().equals(getCurrentPassword())) {


            if (newPassword.getText().equals(confirmPassword.getText())) {
                ArrayList<User> users = labsSystem.getUsers();
                Iterator<User> iterator = users.iterator();

                while (iterator.hasNext()) {
                    User user = iterator.next();
                    if (user.getUserName().equals(getCurrentUserName())) {
                        user.setPassword(newPassword.getText());

                        labsSystem.setUsers(users);
                        setCurrentPassword(newPassword.getText());
                    }
                }

                gotoMainMenu(event);
            }else {
                status.setText("Please re-enter the new password!");
                newPassword.selectAll();
                newPassword.requestFocus();
            }

        }else {
            status.setText("old Password invalid!");
            oldPassword.selectAll();
            oldPassword.requestFocus();
        }
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {
        gotoMainMenu(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.setText(getCurrentUserName());
    }
}
