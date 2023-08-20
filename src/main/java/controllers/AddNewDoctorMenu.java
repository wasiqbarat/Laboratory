package controllers;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    private TextField nationalID;

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
        String nationalIDString = nationalID.getText();

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

        User admin = labsSystem.getUser(usernameString);
        if (admin == null) {
            userNameAuthentication.setText("User not existed!");
            userNameAuthentication.requestFocus();
            return;
        }

        ArrayList<String> doctors = new ArrayList<>();
        for (Doctor doctor : labsSystem.getLaboratory().getDoctors()) {
            doctors.add(doctor.getFirstName() + " " + doctor.getLastName());
        }

        if (admin.getRole().hasAbility(Ability.ADD_DOCTOR) ) {
            if (!admin.getPassword().equals(passwordString)) {
                passwordAuthentication.requestFocus();
                return;
            }

            if (doctors.contains(firstNameString + " " + lastNameString)) {
                firstName.setText("Already exists!");
                firstName.requestFocus();
                return;
            }


            checkIfEmpty(nationalID);
            checkIfEmpty(age);
            checkIfEmpty(address);
            checkIfEmpty(contactNo);
            checkIfEmpty(fatherName);
            checkIfEmpty(lastName);
            checkIfEmpty(firstName);

            int age = 0;
            int contact = 0;
            try {
                contact = Integer.parseInt(contactNoString);
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException ignored) {

            }

            Doctor doctor = new Doctor(firstNameString, lastNameString, fatherNameString, contact, addressString, age, nationalIDString, labsSystem.getLaboratory().getDoctors().size() + 1);

            try {
                labsSystem.getLaboratory().addDoctor(doctor);

                Log log = new Log(getCurrentUserName(), getCurrentPassword(), "Add doctor");
                log.setInfo("New added doctor:\n" + "Name: " + doctor.getFirstName() + " " + doctor.getLastName() + "\nNational ID: " +
                        doctor.getNationalID() + "\nRegister Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a")));
                labsSystem.addLog(log);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


        }else {
            userNameAuthentication.setText("You are not allowed!");
            userNameAuthentication.requestFocus();
        }

    }

    void checkIfEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            textField.requestFocus();
        }
    }

}
