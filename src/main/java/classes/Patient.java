package classes;

public class Patient extends Person{
    protected static final int patientCount = 0;

    public Patient(String firstName, String lastName, int contact, String address, int age, int ID) {
        super(firstName, lastName, contact, address, age, patientCount + 1);
    }

}
