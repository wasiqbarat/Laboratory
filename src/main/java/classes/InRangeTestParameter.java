package classes;

public class InRangeTestParameter extends TestParameter{
    private String result;

    public InRangeTestParameter(String name, String result, String unit, String normalRange) {
        super(name, unit, normalRange);
        this.result = result;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
