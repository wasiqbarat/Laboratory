package controllers;

import classes.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;


import java.net.URL;
import java.util.*;

public class NewPatientMenu extends Controller implements Initializable {
    private String doctorName;
    private String patientSex;
    private String ageUnit;
    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] possibleSuggestions = {"hey", "wasiq", "jan", "salam", "chitor", "good", "hasti", "ok"};
    private Set<String> possibleSuggestionSet = new HashSet<>(Arrays.asList(possibleSuggestions));



    @FXML
    private Label ID;

    @FXML
    private ToggleGroup a;

    @FXML
    private TextField address;

    @FXML
    private TextField age;

    @FXML
    private TitledPane ageTitledPane;

    @FXML
    private TextField contactNo;

    @FXML
    private ChoiceBox<String> doctorsChoiceBox;

    @FXML
    private TextField email;

    @FXML
    private TextField fatherName;

    @FXML
    private Label id;

    @FXML
    private TextField name;

    @FXML
    private TextField processTime;

    @FXML
    private ToggleGroup sex;

    @FXML
    private TextField testsTextField;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton child;

    @FXML
    private RadioButton month;

    @FXML
    private RadioButton day;

    @FXML
    private RadioButton year;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(testsTextField, "hey", "ok", "ab", "cd", "ef");


        initializingDoctors();
        //////////
        initializingAge();
        //////////
        initializingSex();
    }

    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void newButtonPressed(ActionEvent event) {

    }

    @FXML
    void saveButtonPressed(ActionEvent event) {

    }


    private void initializingDoctors() {
        ArrayList<Doctor> doctorsArraylist = labsSystem.getLaboratory().getDoctors();
        ArrayList<String> doctorsNames = new ArrayList<>();

        for (Doctor doctor : doctorsArraylist) {
            doctorsNames.add(doctor.getFirstName() + " " + doctor.getLastName());
        }

        ObservableList<String> doctors = FXCollections.observableArrayList(doctorsNames);
        doctorsChoiceBox.setItems(doctors);
        doctorsChoiceBox.setValue(doctorsNames.get(0));

        doctorsChoiceBox.setOnAction(e -> {
            String selectedValue = doctorsChoiceBox.getValue();
            System.out.println("Selected value: " + selectedValue);
            doctorName = selectedValue;
        });

    }

    private void initializingSex() {
        male.setToggleGroup(sex);
        female.setToggleGroup(sex);
        child.setToggleGroup(sex);

        sex.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected option: " + ( (RadioButton) newValue).getText() );
                patientSex = ((RadioButton) newValue).getText() ;
            }
        });
    }


    private void initializingAge() {
        year.setToggleGroup(a);
        month.setToggleGroup(a);
        day.setToggleGroup(a);

        a.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Age unit Selected option: " + ( (RadioButton) newValue).getText() );
                ageUnit = ((RadioButton) newValue).getText() ;
                ageTitledPane.setText(ageUnit);
            }
        });
    }


}
