package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class SystemInfoMenu extends Controller{

    @FXML
    private TableView<Log> logsTableView;

    @FXML
    private TableColumn<Log, String> dateColumn;

    @FXML
    private TableColumn<Log, String> usernameColumn;

    @FXML
    private TableColumn<Log, String> noColumn;

    @FXML
    private TableColumn<Log, String> actionColumn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Text status;

    @FXML
    void enterButtonPressed(ActionEvent event) {
        if (username.getText().isEmpty()) {
            username.requestFocus();
            return;
        }
        if (password.getText().isEmpty()) {
            password.requestFocus();
            return;
        }

        User admin = labsSystem.getUser(username.getText());
        if (admin == null) {
            username.setText("User not existed!");
            username.requestFocus();
            return;
        }

        if (admin.getRole().hasAbility(Ability.SYSTEM_INFO) ) {
            if (!admin.getPassword().equals(password.getText())) {
                password.requestFocus();
                return;
            }

            ArrayList<Log> logs = labsSystem.getLogs();
            ObservableList<Log> observableList = FXCollections.observableArrayList(logs);

            noColumn.setCellFactory(new LineNumbersCellFactory());

            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
            usernameColumn.setCellFactory(TextFieldTableCell.<Log>forTableColumn());

            dateColumn.setCellValueFactory(new PropertyValueFactory<>("info"));
            dateColumn.setCellFactory(TextFieldTableCell.<Log>forTableColumn());

            actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
            actionColumn.setCellFactory(TextFieldTableCell.<Log>forTableColumn());

            logsTableView.setItems(observableList);


        }else {
            username.setText("You are not allowed!");
            username.requestFocus();
        }

    }

    @FXML
    void mainMenuButtonPressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void moreButtonPressed(ActionEvent event) {

    }


}
