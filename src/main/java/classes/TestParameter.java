package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javax.swing.*;
import java.io.*;

public abstract class TestParameter implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    protected String name;
    protected String unit;
    protected String normalRange;
    protected String testName;
    protected double testPrice;

    public TestParameter(String name, String unit, String normalRange, String testName, double testPrice) {
        this.name = name;
        this.unit = unit;
        this.normalRange = normalRange;
        this.testName = testName;
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

    public static void main(String[] args) throws IOException {
        File file = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/Laboratory/invoice.pdf");

        if (!file.exists()) {
            try {
                DataOutputStream objectOutputStream = new DataOutputStream(new FileOutputStream(file));
                objectOutputStream.write(2);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.printf(new JFileChooser().getFileSystemView().getDefaultDirectory().toString());

    }
}

