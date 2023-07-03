package classes;

public class Patient extends Person{
    protected static final int patientCount = 0;

    public Patient(String firstName, String lastName, String fatherName, int contact, String address, int age, int ID) {
        super(firstName, lastName,fatherName, contact, address, age, patientCount + 1);
    }

}
