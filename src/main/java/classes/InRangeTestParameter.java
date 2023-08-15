package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class InRangeTestParameter extends TestParameter{
    private SimpleStringProperty result;

    public InRangeTestParameter(SimpleStringProperty name, SimpleStringProperty unit, SimpleStringProperty normalRange, SimpleStringProperty result, SimpleStringProperty testName, SimpleDoubleProperty testPrice) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public String getResult() {
        return result.get();
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }


    @Override
    public String toString() {
        return "InRangeTestParameter{" +
                "result=" + result +
                ", name=" + name +
                ", unit=" + unit +
                ", normalRange=" + normalRange +
                ", testName=" + testName +
                ", testPrice=" + testPrice +
                '}';
    }
}
