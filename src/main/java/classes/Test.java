package classes;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public class Test {
    private String name;
    private double price;
    private ArrayList<TestParameter> parameters;

    public Test(String name, double price) {
        this.name = name;
        this.price = price;
        parameters = new ArrayList<>();
    }

    public Test(String name, double price, ArrayList<TestParameter> parameters) {
        this.name = name;
        this.price = price;
        this.parameters = parameters;
    }

    public void addParameter(TestParameter parameter) {
        parameters.add(parameter);
    }

    public Test() {
        parameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<TestParameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<TestParameter> parameters) {
        this.parameters = parameters;
    }
}