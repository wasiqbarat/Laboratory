package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class PositiveNegativeTestParameter extends TestParameter implements Serializable {
    private Boolean result = false;

    public PositiveNegativeTestParameter(String name, String unit, String normalRange, Boolean result, String testName, double testPrice) {
        super(name, unit, normalRange, testName, testPrice);
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
