package controllers;

import classes.InRangeTestParameter;
import classes.LineNumbersCellFactory;
import classes.Test;
import classes.TestParameter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewTestMenu extends Controller implements Initializable {
    private int number = 0;
    private String testNameString;
    private double priceDouble;
    private Test test;
    private ArrayList<TestParameter> parameters = new ArrayList<>();

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private TextField numberOfParameters;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
    }


    private void initializeTable() {
        test = new Test(testNameString, priceDouble);

        for (int i = 0; i < number; i++) {
            TestParameter testParameter = new InRangeTestParameter(new SimpleStringProperty("?"), new SimpleStringProperty("?"), new SimpleStringProperty("?"), new SimpleStringProperty("?"), new SimpleStringProperty(testNameString), new SimpleDoubleProperty(priceDouble) );
            parameters.add(testParameter);
        }

        test.setParameters(parameters);

        ObservableList<TestParameter> list = FXCollections.observableArrayList(parameters);

        no.setCellFactory(new LineNumbersCellFactory());

        testNameColumn.setCellValueFactory(new PropertyValueFactory<>("testName"));
        testNameColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());
        testNameColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((InRangeTestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setTestName(t.getNewValue());
                    testsTableView.requestFocus();
                }
        );

        /////////////////
        parameterColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        parameterColumn.setCellFactory(TextFieldTableCell.<TestParameter>forTableColumn());
        parameterColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<TestParameter, String> t) -> {
                    ((InRangeTestParameter) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setName(t.getNewValue());
                    testsTableView.requestFocus();
                }
        );
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
        no.setEditable(false);
        testNameColumn.setEditable(false);

        testsTableView.setEditable(true);

    }

    @FXML
    void saveButtonPressed(ActionEvent event) {
        if (name.getText().isEmpty()) {
            name.requestFocus();
            return;
        }
        if (price.getText().isEmpty()){
            price.requestFocus();
            return;
        }
        if (numberOfParameters.getText().isEmpty()) {
            numberOfParameters.requestFocus();
            return;
        }

        testNameString = name.getText();
        String priceString = price.getText();

        try {
            priceDouble = Double.parseDouble(priceString);
            test.setPrice(priceDouble);

        } catch (Exception e) {
            price.setText("");
            price.requestFocus();
            return;
        }

        try {
            labsSystem.getLaboratory().addTest(test);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }




    @FXML
    void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void enterButtonPressed(ActionEvent event) {
        if (numberOfParameters.getText().isEmpty()) {
            numberOfParameters.requestFocus();
            return;
        }
        if (name.getText().isEmpty()) {
            name.requestFocus();
            return;
        }
        if (price.getText().isEmpty()) {
            price.requestFocus();
            return;
        }


        testNameString = name.getText();
        String priceString = price.getText();

        try {
            priceDouble = Double.parseDouble(priceString);
            test.setPrice(priceDouble);

        } catch (Exception e) {
            price.setText("");
            price.requestFocus();
            return;
        }

        try {
            number = Integer.parseInt(numberOfParameters.getText());
        }catch (Exception e) {
            numberOfParameters.requestFocus();
        }

        if (parameters.isEmpty()) {
            initializeTable();
        }else {
            parameters.clear();
            initializeTable();
        }

    }

    @FXML
    void priceAction(ActionEvent event) {
        numberOfParameters.requestFocus();
    }

    @FXML
    void testNameAction(ActionEvent event) {
        price.requestFocus();
    }
}
