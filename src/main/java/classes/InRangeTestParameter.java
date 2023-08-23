package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.Serializable;

public class InRangeTestParameter extends TestParameter implements Serializable {
    private String result;


    public InRangeTestParameter(String name, String unit, String normalRange, String result, String testName, double testPrice) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
