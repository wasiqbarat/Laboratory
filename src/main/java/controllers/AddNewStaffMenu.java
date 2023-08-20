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

public class AddNewStaffMenu extends Controller {

    @FXML
    private PasswordField staffPassword;

    @FXML
    private TextField staffUsername;

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
    private TextField job;

    @FXML
    private TextField salary;

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
        String jobString = job.getText();
        String salaryString = salary.getText();

        String staffPasswordString = staffPassword.getText();
        String staffUsernameString = staffUsername.getText();

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

        checkIfEmpty(salary);
        checkIfEmpty(job);
        checkIfEmpty(nationalID);
        checkIfEmpty(staffUsername);
        checkIfEmpty(staffPassword);
        checkIfEmpty(age);
        checkIfEmpty(address);
        checkIfEmpty(contactNo);
        checkIfEmpty(fatherName);
        checkIfEmpty(lastName);
        checkIfEmpty(firstName);

        ArrayList<String> usernames = new ArrayList<>();
        for (User user : labsSystem.getUsers()) {
            usernames.add(user.getUserName());
        }

        User admin = labsSystem.getUser(usernameString);
        if (admin == null) {
            userNameAuthentication.setText("User not existed!");
            userNameAuthentication.requestFocus();
            return;
        }

        if (admin.getRole().hasAbility(Ability.ADD_STAFF) ) {

            if (!admin.getPassword().equals(passwordString)) {
                passwordAuthentication.requestFocus();
                return;
            }

            if (usernames.contains(staffUsernameString)) {
                staffUsername.setText("Already exists!");
                staffUsername.requestFocus();
                return;
            }


            int age = 0;
            int contact = 0;
            try {
                contact = Integer.parseInt(contactNoString);
                age = Integer.parseInt(ageString);
            } catch (NumberFormatException ignored) {

            }

            Staff staff = new Staff(firstNameString, lastNameString, fatherNameString, contact, addressString, age, jobString, nationalIDString, salaryString, labsSystem.getLaboratory().getStaff().size() + 1);


            try {
                labsSystem.getLaboratory().addStaff(staff);

                ArrayList<Ability> staffAbilities = new ArrayList<>();
                staffAbilities.add(Ability.ADD_PATIENT);
                staffAbilities.add(Ability.REMOVE_PATIENT);
                Role staffRole = new Role("staff", staffAbilities);
                User user = new User(staffUsernameString, staffPasswordString, staffRole);
                labsSystem.addUser(user);

                Log log = new Log(getCurrentUserName(), getCurrentPassword(), "Add new staff");
                log.setInfo("New added Staff:\n" + "name: " + firstNameString + " " + lastNameString + "\nNational ID: "
                        + nationalIDString + "\nRegister Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a")));
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

    void checkIfEmpty(PasswordField passwordField) {
        if (passwordField.getText().isEmpty()) {
            passwordField.requestFocus();
        }
    }

}
