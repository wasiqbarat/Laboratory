package controllers;

import classes.Doctor;
import classes.LineNumbersCellFactory;
import classes.Test;
import classes.TestParameter;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
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
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class NewPatientMenu extends Controller implements Initializable {
    private String doctorName = "";
    private String patientSex = "";
    private String ageUnit;
    private LocalDateTime dateTime;
    private ArrayList<String> selectedTests = new ArrayList<>();
    private ArrayList<Test> selectedTest = new ArrayList<>();

    @FXML
    private TextField removeTest;

    @FXML
    private TableView<TestParameter> testsTableView;

    @FXML
    private TableColumn<TestParameter, String> testNameColumn;

    @FXML
    private TableColumn<TestParameter, String> parameterColumn;

    @FXML
    private TableColumn no;

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

    private void initializingRegisterData() {
        dateTime = LocalDateTime.now();

        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd | HH:mm a");

        String loginTime = dateTime.format(formatter);

        data.setText(loginTime);
    }

    private void initializingTests() {
        ArrayList<String> testNames = new ArrayList<>();

        for (Test test : labsSystem.getLaboratory().getTests()) {
            testNames.add(test.getName());
        }

        TextFields.bindAutoCompletion(testsTextField, testNames).setOnAutoCompleted(e -> {
            selectedTest.add(labsSystem.getLaboratory().getTest(testsTextField.getText()));

            selectedTests.add(testsTextField.getText());
            initializeRemoveTests();

            System.out.println("Added Test: " + testsTextField.getText());
            System.out.println("New tests: ");

            for (String name: selectedTests) {
                System.out.println(name);
            }

            testsTextField.setText("");
            initializingTestsTableView();
        });
    }

    private void initializeRemoveTests() {
        TextFields.bindAutoCompletion(removeTest, selectedTests).setOnAutoCompleted(e -> {
            selectedTest.removeIf(test -> test.getName().equals(removeTest.getText()));

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

        no.setCellFactory(new LineNumbersCellFactory());

        ///////////
        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("testName"));
        testNameColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        testNameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTestName(t.getNewValue());
                }
        );
        /////////////////
        parameterColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        parameterColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        parameterColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTestName(t.getNewValue());

                }
        );
        /////////////////
        testResultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        testResultColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        testResultColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTestName(t.getNewValue());

                }
        );
        /////////
        unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        unitColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        unitColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setTestName(t.getNewValue());
                }
        );
        //////
        normalRangeColumn.setCellValueFactory(new PropertyValueFactory<>("normalRange"));
        normalRangeColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());

        normalRangeColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((TestParameter) t.getTableView().getItems().get(

                            t.getTablePosition().getRow())
                    ).setTestName(t.getNewValue());
                    System.out.println(t.getNewValue());
                }

        );


        testsTableView.setItems(list);
        testsTableView.setEditable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializingRegisterData();
        //////////

        //initializeRemoveTests();
        //////////
        initializingTests();
        //////////
        initializingDoctors();
        //////////
        initializingAge();
        //////////
        initializingSex();
        //////

        initializingTestsTableView();
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
