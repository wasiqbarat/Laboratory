package controllers;

import classes.*;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;

import javax.print.*;
import javax.swing.*;
import java.awt.print.PrinterJob;
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
    private int fontSizeInteger;

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
    private Button print;

    @FXML
    private Button save;

    @FXML
    private TextField fontSize;


    private void createAppointment() {
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
            return;
        }

        try {
            intContact = Integer.parseInt(contactNo.getText());
        } catch (Exception e) {
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

        if (selectedTest.isEmpty()) {
            testsTextField.requestFocus();
        } else {
            appointment = new Appointment(patient, doctorName, selectedTest);
            appointment.setAgeUnit(ageUnit);
            appointment.setSex(patientSex);
            appointment.setTotalFee(totalCostDouble);
        }

    }
    @FXML
    void saveButtonPressed(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        //alert.setContentText("Are you sure want to save?!");// line 4


        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        ButtonType saveAndPrintButtonType = new ButtonType("Save and print", ButtonBar.ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(saveButtonType, saveAndPrintButtonType, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty()) {
            return;
        } else if (result.get() == saveButtonType) {
            try {
                createAppointment();
                if (appointment == null) {
                    return;
                }
                labsSystem.getLaboratory().addAppointment(patient, appointment);
                Log log = new Log(getCurrentUserName(), getCurrentPassword(), "Add new patient");
                log.setInfo(appointment.toString());
                labsSystem.addLog(log);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (result.get() == saveAndPrintButtonType) {
            try {
                createAppointment();
                if (appointment == null) {
                    return;
                }

                labsSystem.getLaboratory().addAppointment(patient, appointment);
                Log log = new Log(getCurrentUserName(), getCurrentPassword(), "Add new patient");
                log.setInfo(appointment.toString());
                labsSystem.addLog(log);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                printButtonPressed(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void initializeID(Label label) {
        label.setText(String.valueOf(labsSystem.getLaboratory().getAppointmentsHashMap().size() + 1));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeFontSize();

        initializeID(patientID);

        initializingDoctors();
        initializingAge();
        initializingSex();

        initializingRegisterData();
        //////////
        initializeRemoveTests();
        //////////
        initializingTests();

        initializingTestsTableView();
        testNameColumn.setEditable(false);
        parameterColumn.setEditable(false);

        no.setEditable(false);
    }

    private void initializeFontSize() {

    }


    private void initializingTests() {
        ArrayList<String> testNames = new ArrayList<>();

        for (Test test : labsSystem.getLaboratory().getTests()) {
            testNames.add(test.getName() + "-> " + test.getPrice() + " AF");
        }

        AutoCompletionBinding autoCompletionBinding = TextFields.bindAutoCompletion(testsTextField, testNames);

        autoCompletionBinding.setOnAutoCompleted(e -> {
            String testName;
            testName = testsTextField.getText().split("->")[0];

            if (!selectedTests.contains(testsTextField.getText())) {
                selectedTest.add(labsSystem.getLaboratory().getTest(testName));
                selectedTests.add(testsTextField.getText());
                totalCostDouble += labsSystem.getLaboratory().getTest(testName).getPrice();
                totalCost.setText(String.valueOf(totalCostDouble));
            } else {
                testsTextField.setText("");
                return;
            }

            for (String name : selectedTests) {
                System.out.println(name);
            }

            initializeRemoveTests();
            testsTextField.setText("");
            initializingTestsTableView();
        });
    }

    private void initializeRemoveTests() {
        AutoCompletionBinding autoCompletionBinding = TextFields.bindAutoCompletion(removeTest, selectedTests);

        autoCompletionBinding.setOnAutoCompleted(e -> {
            String testToRemove;
            testToRemove = removeTest.getText().split("-")[0];
            selectedTest.removeIf(test -> test.getName().equals(testToRemove));
            totalCostDouble -= labsSystem.getLaboratory().getTest(testsTextField.getText()).getPrice();
            totalCost.setText(String.valueOf(totalCostDouble));

            selectedTests.remove(removeTest.getText());

            for (String name : selectedTests) {
                System.out.println(name);
            }

            removeTest.setText("");
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Data should be deleted!");
        //alert.setContentText("Are you sure want to close?!");// line 4

        ButtonType continueToAddPatient = new ButtonType("Continue to add patient", ButtonBar.ButtonData.OK_DONE);
        ButtonType close = new ButtonType("Close", ButtonBar.ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(continueToAddPatient, close);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == close) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

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

            } catch (Exception e) {
                doctorName = "";
                e.printStackTrace();
            }

            doctorsChoiceBox.setOnAction(e -> {
                String selectedValue = doctorsChoiceBox.getValue();
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
                    patientSex = ((RadioButton) newValue).getText();
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
                    ageUnit = ((RadioButton) newValue).getText();
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

        @FXML
        void printButtonPressed(ActionEvent event) {
            if (appointment == null) {
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
                    return;
                }

                try {
                    intContact = Integer.parseInt(contactNo.getText());
                } catch (Exception e) {
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

                if (selectedTest.isEmpty()) {
                    testsTextField.requestFocus();
                    return;
                } else {
                    appointment = new Appointment(patient, doctorName, selectedTest);
                    appointment.setAgeUnit(ageUnit);
                    appointment.setSex(patientSex);
                    appointment.setTotalFee(totalCostDouble);
                }
            }

            try {
                fontSizeInteger = Integer.parseInt(fontSize.getText());
            } catch (Exception e) {
                fontSizeInteger = 11;
                fontSize.requestFocus();
                return;
            }

            File theDir = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/LaboratoryPatients");
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            String path = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/LaboratoryPatients/" + patient.getFirstName() + patient.getLastName() + ".pdf";

            ArrayList<InRangeTestParameter> parameters = new ArrayList<>();
            for (Test test : selectedTest) {
                for (TestParameter testParameter : test.getParameters()) {
                    parameters.add((InRangeTestParameter) testParameter);
                }
            }

            try {
                PdfWriter pdfWriter = new PdfWriter(path);
                PdfDocument pdfDoc = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDoc);

                String headerPath = "C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Laboratory\\src\\main\\resources\\assets\\Header.jpg";

                ImageData data = ImageDataFactory.create(headerPath);
                document.add(new com.itextpdf.layout.element.Image(data).setMargins(0, 0, 20, 0));
                //document.add(new com.itextpdf.layout.element.Image(data).setFixedPosition(document.getRightMargin(), document.getRightMargin()));

                float[] identityWidths = {65F, 120F, 58F, 120F, 60F, 100F};
                Table identityTable = new Table(identityWidths);
                identityTable.setMarginTop(10);

                createIdentityTable(identityTable);

                document.add(identityTable);

                //// Table
                float[] pointColumnWidths = {250F, 200F, 70F, 70F, 100F};
                Table table = new Table(pointColumnWidths);
                table.setMarginTop(15);

                Cell testName = new Cell().setFontSize(fontSizeInteger).setBold();
                testName.add("Test name");
                testName.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(testName);

                Cell parameter = new Cell().setFontSize(fontSizeInteger).setBold();
                parameter.add("Parameter");
                parameter.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(parameter);


                Cell result = new Cell().setFontSize(fontSizeInteger).setBold();
                result.add("Result");
                result.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(result);

                Cell unit = new Cell().setFontSize(fontSizeInteger).setBold();
                unit.add("Unit");

                unit.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(unit);

                Cell normalRange = new Cell().setFontSize(fontSizeInteger).setBold();
                normalRange.add("Normal range");
                normalRange.setBackgroundColor(Color.LIGHT_GRAY);
                table.addCell(normalRange);

                for (InRangeTestParameter testParameter : parameters) {
                    if (testParameter.getResult().trim().isEmpty()) {
                        continue;
                    }
                    table.addCell(testParameter.getTestName()).setFontSize(fontSizeInteger);
                    table.addCell(testParameter.getName()).setFontSize(fontSizeInteger);
                    table.addCell(testParameter.getResult()).setFontSize(fontSizeInteger);
                    table.addCell(testParameter.getUnit()).setFontSize(fontSizeInteger);
                    table.addCell(testParameter.getNormalRange()).setFontSize(fontSizeInteger);
                }

                document.add(table);

                Paragraph wasiq = new Paragraph("Developed by: Wasiq Barat");


                document.add(wasiq.setFontSize(7));
                document.close();

                //Printing invoice
                PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                PDDocument document1 = Loader.loadPDF(new File(path));

                PrinterJob printerjob = PrinterJob.getPrinterJob();
                printerjob.setPageable(new PDFPageable(document1));
                printerjob.setPrintService(defaultService);
                printerjob.print();
                document1.close();
                System.out.printf("Done!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void createIdentityTable(Table identityTable) {
            //identityTable.setBorder();

            Cell idCell = new Cell();
            idCell.add("Patient ID:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(idCell);

            Cell id = new Cell();
            id.add(String.valueOf(patient.getID())).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(id);

            Cell refferedByCell = new Cell();
            refferedByCell.add("Referred By:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(refferedByCell);

            Cell referredBy = new Cell();
            referredBy.add(String.valueOf(appointment.getDoctor())).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(referredBy);

            Cell processTimeCell = new Cell();
            processTimeCell.add("Process time:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(processTimeCell);

            Cell processTime = new Cell();
            processTime.add("").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(processTime);


            Cell patientNameCell = new Cell();
            patientNameCell.add("Patient Name:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(patientNameCell);

            Cell patientName1 = new Cell();
            patientName1.add(patient.getFirstName() + " " +
                    patient.getLastName()).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(patientName1);

            Cell contactCell = new Cell();
            contactCell.add("Contact:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(contactCell);

            Cell contact = new Cell();
            contact.add(String.valueOf(patient.getContact())).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(contact);

            Cell addressCell = new Cell();
            addressCell.add("Address:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(addressCell);

            Cell address = new Cell();
            address.add(patient.getAddress()).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(address);

            Cell fatherNameCell = new Cell();
            fatherNameCell.add("Father name:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(fatherNameCell);

            Cell fatherName = new Cell();
            fatherName.add(patient.getFatherName()).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(fatherName);

            Cell ageSexCell = new Cell();
            ageSexCell.add("Age / Sex:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(ageSexCell);

            Cell ageSex = new Cell();
            ageSex.add(patient.getAge() + "(" + appointment.getAgeUnit() + "s) / " + appointment.getSex()).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(ageSex);


            Cell dateCell = new Cell();
            dateCell.add("Date:").setFontSize(fontSizeInteger - 1).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(dateCell);

            Cell date1 = new Cell();
            date1.add(appointment.getAppointmentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a"))).setFontSize(fontSizeInteger - 1).setBorder(Border.NO_BORDER);
            identityTable.addCell(date1);
        }
    }
