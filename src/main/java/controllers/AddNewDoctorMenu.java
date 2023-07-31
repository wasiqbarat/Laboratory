package controllers;

import classes.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewDoctorMenu extends Controller{

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TextField contactNo;

    @FXML
    private TextField fatherName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField passwordAuthentication;

    @FXML
    private TextField userNameAuthentication;

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveButtonPressed(ActionEvent event) {
        String firstNameString = firstName.getText();
        String lastNameString = lastName.getText();
        String fatherNameString = fatherName.getText();
        String addressString = address.getText();
        String contactNoString = contactNo.getText();
        String ageString = age.getText();


        String passwordString = passwordAuthentication.getText();
        String usernameString = userNameAuthentication.getText();

        if (usernameString.isEmpty()) {
            userNameAuthentication.requestFocus();
            return;
        }
        if (passwordString.isEmpty()) {
            passwordAuthentication.requestFocus();
            return;
        }


        if (labsSystem.authentication(usernameString, passwordString)) {
            checkIfEmpty(firstName);
            checkIfEmpty(lastName);
            checkIfEmpty(fatherName);
            checkIfEmpty(contactNo);
            checkIfEmpty(address);
            checkIfEmpty(age);
            int age = 0;
            int contact = 0;
            try {
                contact = Integer.parseInt(contactNoString);
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException ignored) {

            }

            Doctor doctor = new Doctor(firstNameString, lastNameString, fatherNameString, contact, addressString, age);


            try {
                labsSystem.getLaboratory().addDoctor(doctor);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            } catch (Exception e) {

            }


        }else {
            userNameAuthentication.setText("invalid user or password!");
            userNameAuthentication.requestFocus();
        }
    }

    void checkIfEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            textField.requestFocus();
        }
    }

}
