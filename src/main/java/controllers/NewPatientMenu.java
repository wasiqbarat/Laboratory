package controllers;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NewPatientMenu extends Controller implements Initializable {
    private String doctorName;
    private String patientSex;
    private String ageUnit;
    private LocalDateTime dateTime;
    private ArrayList<String> selectedTests = new ArrayList<>();
    private ArrayList<Test> selectedTest = new ArrayList<>();
    private Patient patient = null;
    private Appointment appointment = null;
    private double totalCostDouble = 0;


    @FXML
    private TextField totalCost;

    @FXML
    private TextField removeTest;

    @FXML
    private TableView<TestParameter> testsTableView;

    @FXML
    private TableColumn<TestParameter, String> testNameColumn;

    @FXML
    private TableColumn<TestParameter, String> parameterColumn;

    @FXML
    private TableColumn<TestParameter, Integer> no;

    @FXML
    private TableColumn<TestParameter, String> normalRangeColumn;

    @FXML
    private TableColumn<TestParameter, String> testResultColumn;

    @FXML
    private TableColumn<TestParameter, String> unitColumn;

    @FXML
    private Label patientID;

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
    private Label data;

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

    @FXML
    void saveButtonPressed(ActionEvent event) {
        String nameString = name.getText();
        String fatherNameString = fatherName.getText();
        String contactString = contactNo.getText();
        String addressString = address.getText();
        String processTimeString = processTime.getText();
        String emailString = email.getText();

        if (name.getText().isEmpty()) {
            name.requestFocus();
            return;
        }

        if (fatherName.getText().isEmpty()) {
            fatherName.requestFocus();
            return;
        }
        if (age.getText().isEmpty()) {
            age.requestFocus();
            return;
        }


        int intAge = 0;
        int intContact;

        try {
            intAge = Integer.parseInt(age.getText());
        } catch (Exception e) {
            age.requestFocus();
            e.printStackTrace();
        }

        try {
            intContact = Integer.parseInt(contactNo.getText());
        }catch (Exception e) {
            intContact = 0;
        }

        switch (patientSex) {
            case "Male" -> {
                patient = new MalePatient(nameString, "", fatherNameString, intContact, addressString, intAge, labsSystem.getLaboratory().getAppointmentsHashMap().size() + 1);
            }
            case "Female" -> {
                patient = new femalePatient(nameString, "", fatherNameString, intContact, addressString, intAge, labsSystem.getLaboratory().getAppointmentsHashMap().size() + 1);
            }
            case "Child" -> {
                patient = new childPatient(nameString, "", fatherNameString, intContact, addressString, intAge, labsSystem.getLaboratory().getAppointmentsHashMap().size() + 1);
            }
        }

        patient.setAgeUnit(ageUnit);
        if (selectedTest.isEmpty()) {
            testsTextField.requestFocus();
            return;
        } else {
            appointment = new Appointment(patient, doctorName, selectedTest);
            appointment.setTotalFee(totalCostDouble);
        }

        ///{{{{ a popup menu}}}}}}}

        try {
            labsSystem.getLaboratory().addAppointment(patient, appointment);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initializeID(Label label) {
        label.setText(String.valueOf(labsSystem.getLaboratory().getAppointmentsHashMap().size() + 1));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeID(patientID);

        initializingDoctors();
        initializingAge();
        initializingSex();


        initializingRegisterData();
        //////////
        //initializeRemoveTests();
        //////////
        initializingTests();

        initializingTestsTableView();
        testNameColumn.setEditable(false);
        parameterColumn.setEditable(false);

        no.setEditable(false);
    }

    private void initializingTests() {
        ArrayList<String> testNames = new ArrayList<>();

        for (Test test : labsSystem.getLaboratory().getTests()) {
            testNames.add(test.getName() + "-> " + test.getPrice() + " AF");
        }

        TextFields.bindAutoCompletion(testsTextField, testNames).setOnAutoCompleted(e -> {
            String testName;
            testName = testsTextField.getText().split("->")[0];

            if (!selectedTests.contains(testsTextField.getText())) {
                selectedTest.add(labsSystem.getLaboratory().getTest(testName));
                selectedTests.add(testsTextField.getText());
                totalCostDouble += labsSystem.getLaboratory().getTest(testName).getPrice();
                totalCost.setText(String.valueOf(totalCostDouble));


            }else {
                testsTextField.setText("");
                return;
            }


            System.out.println("Added Test: " + testsTextField.getText());
            System.out.println("New tests: ");

            for (String name: selectedTests) {
                System.out.println(name);
            }

            initializeRemoveTests();
            testsTextField.setText("");
            initializingTestsTableView();
        });
    }

    private void initializingRegisterData() {
        dateTime = LocalDateTime.now();

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd | HH:mm a");

        String loginTime = dateTime.format(formatter);

        data.setText(loginTime);
    }

    private void initializeRemoveTests() {
        TextFields.bindAutoCompletion(removeTest, selectedTests).setOnAutoCompleted(e -> {
            String testToRemove;
            testToRemove = removeTest.getText().split("-")[0];
            selectedTest.removeIf(test -> test.getName().equals(testToRemove));
            totalCostDouble -= labsSystem.getLaboratory().getTest(testsTextField.getText()).getPrice();
            totalCost.setText(String.valueOf(totalCostDouble));

            selectedTests.remove(removeTest.getText());

            System.out.println("Removed test: " + removeTest.getText());
            System.out.println("Tests: ");

            for (String name: selectedTests) {
                System.out.println(name);
            }

            removeTest.setText("");
            initializingTestsTableView();
        });
    }

    private void initializingTestsTableView() {
        ArrayList<TestParameter> testParameters = new ArrayList<>();

        for (Test test : selectedTest) {
            testParameters.addAll(test.getParameters());
        }

        ObservableList<TestParameter> list = FXCollections.observableArrayList(testParameters);
        /////////////

        // initializing row counter column
        no.setCellFactory(new LineNumbersCellFactory());

        ///////////
        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("testName"));
        testNameColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());
        /////////////////
        parameterColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        parameterColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());
        ////////////////

        testResultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        testResultColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());


        testResultColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((InRangeTestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setResult(t.getNewValue());
                    testsTableView.requestFocus();
                }
        );

        /////////
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        unitColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setUnit(t.getNewValue());
                    testsTableView.requestFocus();
                }
        );
        ////////

        normalRangeColumn.setCellValueFactory(new PropertyValueFactory<>("normalRange"));
        normalRangeColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        normalRangeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setNormalRange(t.getNewValue());
                    testsTableView.requestFocus();
                }
        );

        testsTableView.setItems(list);
        testsTableView.setEditable(true);
    }


    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void newButtonPressed(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/controllers/NewPatientMenu.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();

        Scene scene = new Scene(root);
        stage.setTitle("Sohail laboratory (Add new patient)");

        stage.getIcons().add(new Image("C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Pharmacy\\assets\\report.png"));

        stage.setScene(scene);
        stage.setMaximized(false);
        //stage.setWidth(900);
        //stage.setHeight(550);
        stage.show();
    }


    private void initializingDoctors() {
        ArrayList<Doctor> doctorsArraylist = labsSystem.getLaboratory().getDoctors();
        ArrayList<String> doctorsNames = new ArrayList<>();

        for (Doctor doctor : doctorsArraylist) {
            doctorsNames.add(doctor.getFirstName() + " " + doctor.getLastName());
        }

        ObservableList<String> doctors = FXCollections.observableArrayList(doctorsNames);
        doctorsChoiceBox.setItems(doctors);

        try {
            doctorsChoiceBox.setValue(doctorsNames.get(0));
            doctorName = doctorsNames.get(0);

        }catch (Exception e) {
            doctorName = "";
            e.printStackTrace();
        }


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

        patientSex = "Male";

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

        ageUnit = "Year";

        a.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Age unit Selected option: " + ( (RadioButton) newValue).getText() );
                ageUnit = ((RadioButton) newValue).getText() ;
                ageTitledPane.setText(ageUnit);
            }
        });


    }

    @FXML
    void testsTextFieldAction(ActionEvent event) {
        testsTableView.requestFocus();
    }

    @FXML
    void ageTextFieldAction(ActionEvent event) {
        testsTextField.requestFocus();
    }

    @FXML
    void fatherNameTextFieldAction(ActionEvent event) {
        age.requestFocus();
    }

    @FXML
    void nameTextFieldAction(ActionEvent event) {
        fatherName.requestFocus();
    }

}
