package controllers;

import classes.*;
import java.awt.*;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
    private Button print;

    @FXML
    private Button save;



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
            return;
        }

        try {
            intContact = Integer.parseInt(contactNo.getText());
        }catch (Exception e) {
            contactNo.requestFocus();
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

        ///{{{{ a popup menu}}}}}}}

        try {
            labsSystem.getLaboratory().addAppointment(patient, appointment);

            Log log = new Log(getCurrentUserName(), getCurrentPassword(), "Add new patient");
            log.setInfo(appointment.toString());
            labsSystem.addLog(log);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            //stage.close();
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
        initializeRemoveTests();
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

        AutoCompletionBinding autoCompletionBinding = TextFields.bindAutoCompletion(testsTextField, testNames);

        autoCompletionBinding.setOnAutoCompleted(e -> {
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

    private void initializeRemoveTests() {
        AutoCompletionBinding autoCompletionBinding = TextFields.bindAutoCompletion(removeTest, selectedTests);

        autoCompletionBinding.setOnAutoCompleted(e -> {
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

    @FXML
    void printButtonPressed(ActionEvent event) {
        if (appointment == null) {
            save.requestFocus();
            return;
        }
        String path = "C:/Users/wasiq/OneDrive/Desktop/PDFs/pdf2.pdf";


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

            float [] identityWidths = {65F, 120F, 58F, 120F, 60F, 100F};
            Table identityTable = new Table(identityWidths);
            identityTable.setMarginTop(10);


            //identityTable.setBorder();

            Cell idCell = new Cell();
            idCell.add("Patient ID:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(idCell);

            Cell id = new Cell();
            id.add(String.valueOf(patient.getID())).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(id);

            Cell refferedByCell = new Cell();
            refferedByCell.add("Referred By:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(refferedByCell);

            Cell referredBy = new Cell();
            referredBy.add(String.valueOf(appointment.getDoctor())).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(referredBy);

            Cell processTimeCell = new Cell();
            processTimeCell.add(String.valueOf("Process time:")).setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(processTimeCell);

            Cell processTime = new Cell();
            processTime.add("").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(processTime);


            Cell patientNameCell = new Cell();
            patientNameCell.add("Patient Name:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(patientNameCell);

            Cell patientName1 = new Cell();
            patientName1.add(patient.getFirstName() + " " + patient.getLastName()).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(patientName1);

            Cell contactCell = new Cell();
            contactCell.add("Contact:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(contactCell);

            Cell contact = new Cell();
            contact.add(String.valueOf(patient.getContact())).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(contact);

            Cell emailCell = new Cell();
            emailCell.add("Email:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(emailCell);

            Cell email1 = new Cell();
            email1.add("email").setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(email1);

            Cell ageSexCell = new Cell();
            ageSexCell.add("Age / Sex:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(ageSexCell);

            Cell ageSex = new Cell();
            ageSex.add(patient.getAge() + "/" + appointment.getSex()).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(ageSex);

            Cell addressCell = new Cell();
            addressCell.add("Address:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(addressCell);

            Cell address = new Cell();
            address.add(patient.getAddress()).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(address);

            Cell dateCell = new Cell();
            dateCell.add("Date:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(dateCell);

            Cell date1 = new Cell();
            date1.add(appointment.getAppointmentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a"))).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(date1);


            document.add(identityTable);

            //// Table
            float [] pointColumnWidths = {20F, 200F, 200F, 70F, 70F, 100F};
            Table table = new Table(pointColumnWidths);
            table.setMarginTop(15);

            Cell no = new Cell().setFontSize(10).setBold();
            no.add("No");
            no.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(no);

            Cell testName = new Cell().setFontSize(10).setBold();
            testName.add("Test name");
            testName.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(testName);

            Cell parameter = new Cell().setFontSize(10).setBold();
            parameter.add("Parameter");
            parameter.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(parameter);


            Cell result = new Cell().setFontSize(10).setBold();
            result.add("Result");
            result.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(result);

            Cell unit = new Cell().setFontSize(10).setBold();
            unit.add("Unit");

            unit.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(unit);

            Cell normalRange = new Cell().setFontSize(10).setBold();
            normalRange.add("Normal range");
            normalRange.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(normalRange);

            int counter = 1;
            for (InRangeTestParameter testParameter : parameters) {
                table.addCell(String.valueOf(counter)).setFontSize(10);
                counter++;

                table.addCell(testParameter.getTestName()).setFontSize(10);

                table.addCell(testParameter.getName()).setFontSize(10);
                table.addCell(testParameter.getResult()).setFontSize(10);
                table.addCell(testParameter.getUnit()).setFontSize(10);
                table.addCell(testParameter.getNormalRange()).setFontSize(10);
            }

            document.add(table);

            Paragraph wasiq = new Paragraph("Developed by: Wasiq Barat");

            document.add(wasiq.setFontSize(7));
            document.close();

            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            PDDocument document1 = Loader.loadPDF(new File(path));

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document1));
            job.setPrintService(defaultService);
            job.print();
            document1.close();


            System.out.printf("Done!");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String path = "C:/Users/wasiq/OneDrive/Desktop/PDFs/pdf2.pdf";


        //////
        ArrayList<InRangeTestParameter> parameters = new ArrayList<>();
        ArrayList<Test> tests = new ArrayList<>();


        Test Brucellosis = new Test("BRUCELLOSIS", 200);
        Test aptt = new Test("ACTIVATED PARCIAL THROMBIN TIM", 400);
        TestParameter brucelosisAbortus = new InRangeTestParameter(new SimpleStringProperty("BRUCELOSIS Abortus"), new SimpleStringProperty(""), new SimpleStringProperty("1:80"), new SimpleStringProperty("1:20"), new SimpleStringProperty("BRUCELLOSIS"), new SimpleDoubleProperty(200) );
        TestParameter brucelosisMelitensis = new InRangeTestParameter(new SimpleStringProperty("BRUCELOSIS Melitensis"),new SimpleStringProperty(""),new SimpleStringProperty("1:80"),new SimpleStringProperty( "1:20"),new SimpleStringProperty("BRUCELLOSIS"), new SimpleDoubleProperty(200));
        parameters.add((InRangeTestParameter) brucelosisAbortus);
        parameters.add((InRangeTestParameter) brucelosisMelitensis);
        TestParameter pt = new InRangeTestParameter(new SimpleStringProperty("PT (PATIENT)"), new SimpleStringProperty("Sec"), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty("ACTIVATED PARCIAL THROMBIN TIM"), new SimpleDoubleProperty(400));
        TestParameter ptC = new InRangeTestParameter(new SimpleStringProperty("PT CONTROL"), new SimpleStringProperty("Sec"), new SimpleStringProperty(""), new SimpleStringProperty("14"), new SimpleStringProperty("ACTIVATED PARCIAL THROMBIN TIM"), new SimpleDoubleProperty(400));
        TestParameter INR = new InRangeTestParameter(new SimpleStringProperty("INR"),new SimpleStringProperty( ""), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty("ACTIVATED PARCIAL THROMBIN TIM"), new SimpleDoubleProperty(400));
        parameters.add((InRangeTestParameter) pt);
        parameters.add((InRangeTestParameter) ptC);
        parameters.add((InRangeTestParameter) INR);

        aptt.addParameter(pt);
        aptt.addParameter(ptC);
        aptt.addParameter(INR);
        Brucellosis.addParameter(brucelosisAbortus);
        Brucellosis.addParameter(brucelosisMelitensis);

        tests.add(Brucellosis);
        tests.add(aptt);

        Patient patient = new MalePatient("Majid", "nadim", "mohammad", 121212, "", 25, 12);
        Appointment appointment = new Appointment(patient, "Samadi", tests);
        //////////


        try {
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDoc);

            String headerPath = "C:\\Users\\wasiq\\OneDrive\\Desktop\\Programming projects\\Laboratory\\src\\main\\resources\\assets\\Header.jpg";

            ImageData data = ImageDataFactory.create(headerPath);
            document.add(new com.itextpdf.layout.element.Image(data).setMargins(0, 0, 20, 0));
            //document.add(new com.itextpdf.layout.element.Image(data).setFixedPosition(document.getRightMargin(), document.getRightMargin()));

            float [] identityWidths = {65F, 120F, 58F, 120F, 60F, 100F};
            Table identityTable = new Table(identityWidths);
            identityTable.setMarginTop(10);


            //identityTable.setBorder();

            Cell idCell = new Cell();
            idCell.add("Patient ID:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(idCell);

            Cell id = new Cell();
            id.add(String.valueOf(patient.getID())).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(id);

            Cell refferedByCell = new Cell();
            refferedByCell.add("Referred By:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(refferedByCell);

            Cell referredBy = new Cell();
            referredBy.add(String.valueOf(appointment.getDoctor())).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(referredBy);

            Cell processTimeCell = new Cell();
            processTimeCell.add(String.valueOf("Process time:")).setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(processTimeCell);

            Cell processTime = new Cell();
            processTime.add("").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(processTime);


            Cell patientNameCell = new Cell();
            patientNameCell.add("Patient Name:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(patientNameCell);

            Cell patientName1 = new Cell();
            patientName1.add(patient.getFirstName() + " " + patient.getLastName()).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(patientName1);

            Cell contactCell = new Cell();
            contactCell.add("Contact:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(contactCell);

            Cell contact = new Cell();
            contact.add(String.valueOf(patient.getContact())).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(contact);

            Cell emailCell = new Cell();
            emailCell.add("Email:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(emailCell);

            Cell email1 = new Cell();
            email1.add("email").setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(email1);

            Cell ageSexCell = new Cell();
            ageSexCell.add("Age / Sex:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(ageSexCell);

            Cell ageSex = new Cell();
            ageSex.add(patient.getAge() + "/" + appointment.getSex()).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(ageSex);

            Cell addressCell = new Cell();
            addressCell.add("Address:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(addressCell);

            Cell address = new Cell();
            address.add(patient.getAddress()).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(address);

            Cell dateCell = new Cell();
            dateCell.add("Date:").setFontSize(9).setBold().setBorder(Border.NO_BORDER);
            identityTable.addCell(dateCell);

            Cell date1 = new Cell();
            date1.add(appointment.getAppointmentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a"))).setFontSize(9).setBorder(Border.NO_BORDER);
            identityTable.addCell(date1);


            document.add(identityTable);

            //// Table
            float [] pointColumnWidths = {20F, 200F, 200F, 70F, 70F, 100F};
            Table table = new Table(pointColumnWidths);
            table.setMarginTop(15);

            Cell no = new Cell().setFontSize(10).setBold();
            no.add("No");
            no.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(no);

            Cell testName = new Cell().setFontSize(10).setBold();
            testName.add("Test name");
            testName.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(testName);

            Cell parameter = new Cell().setFontSize(10).setBold();
            parameter.add("Parameter");
            parameter.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(parameter);


            Cell result = new Cell().setFontSize(10).setBold();
            result.add("Result");
            result.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(result);

            Cell unit = new Cell().setFontSize(10).setBold();
            unit.add("Unit");

            unit.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(unit);

            Cell normalRange = new Cell().setFontSize(10).setBold();
            normalRange.add("Normal range");
            normalRange.setBackgroundColor(Color.LIGHT_GRAY);
            table.addCell(normalRange);

            int counter = 1;
            for (InRangeTestParameter testParameter : parameters) {
                table.addCell(String.valueOf(counter)).setFontSize(10);
                counter++;

                table.addCell(testParameter.getTestName()).setFontSize(10);

                table.addCell(testParameter.getName()).setFontSize(10);
                table.addCell(testParameter.getResult()).setFontSize(10);
                table.addCell(testParameter.getUnit()).setFontSize(10);
                table.addCell(testParameter.getNormalRange()).setFontSize(10);
            }

            document.add(table);

            Paragraph wasiq = new Paragraph("Developed by: Wasiq Barat");

            document.add(wasiq.setFontSize(7));
            document.close();

            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            PDDocument document1 = Loader.loadPDF(new File(path));

            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document1));
            job.setPrintService(defaultService);
            job.print();
            document1.close();


            System.out.printf("Done!");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
