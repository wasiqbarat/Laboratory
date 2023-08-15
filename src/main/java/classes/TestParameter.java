package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class TestParameter {
    protected SimpleStringProperty name;
    protected SimpleStringProperty unit;
    protected SimpleStringProperty normalRange;
    protected SimpleStringProperty testName;
    protected SimpleDoubleProperty testPrice;

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

    public void setName(String name) {
        this.name.set(name);
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public void setNormalRange(String normalRange) {
        this.normalRange.set(normalRange);
    }

    public void setTestPrice(double testPrice) {
        this.testPrice.set(testPrice);
    }

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
