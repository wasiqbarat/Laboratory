package classes;

public abstract class Patient extends Person{
    protected final int ID;
    protected String ageUnit;

    public Patient(String firstName, String lastName, String fatherName, int contact, String address, int age, int ID) {
        super(firstName, lastName,fatherName, contact, address, age);
        this.ID = ID;
    }

    @Override
    public String toString() {
        return super.toString() + "ID : " + ID;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }
}
