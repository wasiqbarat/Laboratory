package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class PositiveNegativeTestParameter extends TestParameter{
    private Boolean result = false;

    public PositiveNegativeTestParameter(SimpleStringProperty name, SimpleStringProperty unit, SimpleStringProperty normalRange, SimpleStringProperty testName, SimpleDoubleProperty testPrice) {
        super(name, unit, normalRange, testName, testPrice);
    }

    //private SimpleBooleanProperty result = false;

   /* public PositiveNegativeTestParameter(String name, String unit, String normalRange, boolean result, String testName, double testPrice) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }*/




    /*public PositiveNegativeTestParameter(String name) {
        super(name);
    }*/

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
