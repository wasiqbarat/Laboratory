package classes;

public class PositiveNegativeTestParameter extends TestParameter{
    private boolean result = false;

    public PositiveNegativeTestParameter(String name, boolean result, String unit, String normalRange) {
        super(name, unit, normalRange);
        this.result = result;
    }

    public PositiveNegativeTestParameter(String name) {
        super(name);
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
