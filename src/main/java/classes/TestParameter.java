package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class TestParameter {
  /*  protected String name;
    protected String unit;
    protected String normalRange;
    protected String testName;
    protected double testPrice;*/

    public SimpleStringProperty name;
    public SimpleStringProperty unit;
    public SimpleStringProperty normalRange;
    public SimpleStringProperty testName;
    public SimpleDoubleProperty testPrice;


  /*  public TestParameter(String name, String unit, String normalRange, String testName, double testPrice) {
        this.name = name;
        this.unit = unit;
        this.normalRange = normalRange;
        this.testName = testName;
        this.testPrice = testPrice;
    }*/

    public TestParameter(SimpleStringProperty name, SimpleStringProperty unit, SimpleStringProperty normalRange, SimpleStringProperty testName, SimpleDoubleProperty testPrice) {
        this.name = name;
        this.unit = unit;
        this.normalRange = normalRange;
        this.testName = testName;
        this.testPrice = testPrice;
    }

    public void setTestName(String newValue) {
        testName = new SimpleStringProperty(newValue);
    }

/*    public TestParameter(String name) {
        this.name = name;
        unit = "";
        normalRange = "";
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(double testPrice) {
        this.testPrice = testPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(String normalRange) {
        this.normalRange = normalRange;
    }*/

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public String getNormalRange() {
        return normalRange.get();
    }

    public SimpleStringProperty normalRangeProperty() {
        return normalRange;
    }

    public String getTestName() {
        return testName.get();
    }

    public SimpleStringProperty testNameProperty() {
        return testName;
    }

    public double getTestPrice() {
        return testPrice.get();
    }

    public SimpleDoubleProperty testPriceProperty() {
        return testPrice;
    }
}
