package classes;

public abstract class TestParameter {
    protected String name;
    protected String unit;
    protected String normalRange;

    public TestParameter(String name, String unit, String normalRange) {
        this.name = name;
        this.unit = unit;
        this.normalRange = normalRange;
    }

    public TestParameter(String name) {
        this.name = name;
        unit = "";
        normalRange = "";
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

}
