package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class MainMenu extends Controller implements Initializable {

    @FXML
    private Button exit;

    @FXML
    private Button newPatient;

    @FXML
    private Button profile;

    @FXML
    private TextField footer;

    @FXML
    private Button searchPatient;

    @FXML
    void ProfileButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/ProfileMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();

    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void newPatientButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/NewPatientMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

     /*   stage.setMaximized(false);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.setHeight(550);
        stage.setWidth(900);
        */
        Scene scene = new Scene(root);
        stage.setTitle("Sohail laboratory (Add new patient)");

        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Pharmacy\\assets\\report.png"));

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void searchPatientButtonPressed(ActionEvent event) {

    }

    @FXML
    void financialDataButtonPressed(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeFooter(footer);
    }

    private void initializeFooter(TextField textField) {
        String log;
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd | HH:mm a");
        String loginTime = LocalDateTime.now().format(formatter);
        String identity = "You are logged in as (" + getCurrentUserName() + ")";

        log = identity +  "        login time(زمان ورود): ".concat(loginTime);

        footer.setText(log);
    }

    @FXML
    void LogOutButtonPressed(ActionEvent event) throws IOException {
        setCurrentPassword(null);
        setCurrentUserName(null);

        URL url = new File("src/main/resources/controllers/firstMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setHeight(450);
        stage.setWidth(600);

        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void addNewDoctorButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/addNewDoctorMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("Sohail laboratory");

        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Pharmacy\\assets\\report.png"));

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void addNewStaffButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/addNewStaffMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("Sohail laboratory");

        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Pharmacy\\assets\\report.png"));

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addNewTestButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/addNewTestMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("Sohail laboratory (Add new Test)");

        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Pharmacy\\assets\\report.png"));

        stage.setScene(scene);
        stage.setMaximized(false);

        stage.show();

    }

    @FXML
    void systemInfoButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/systemInfoMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("Sohail laboratory (System info)");

        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Pharmacy\\assets\\report.png"));

        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setWidth(900);
        stage.setHeight(550);
        stage.show();
    }
}
