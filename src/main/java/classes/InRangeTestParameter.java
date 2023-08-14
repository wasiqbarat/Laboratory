package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class InRangeTestParameter extends TestParameter{
    private SimpleStringProperty result;
    //private String result;


   /* public InRangeTestParameter(String name, String unit, String normalRange, String testName, double testPrice, SimpleStringProperty result) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }*/

    public InRangeTestParameter(SimpleStringProperty name, SimpleStringProperty unit, SimpleStringProperty normalRange, SimpleStringProperty result, SimpleStringProperty testName, SimpleDoubleProperty testPrice) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }

    public String getResult() {
        return result.get();
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }

    /* public InRangeTestParameter(String name, SimpleStringProperty result) {
        super(name);
        this.result = result;
    }*/


 /*   public InRangeTestParameter(String name, String unit, String normalRange, String result, String testName, double testPrice) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
*/

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
