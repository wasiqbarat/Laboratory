package classes;

public class Doctor extends Person {
    protected static final int doctorCount = 0;

    public Doctor(String firstName, String lastName, String fatherName, int contact, String address, int age, int ID) {
        super(firstName, lastName, fatherName, contact, address, age, doctorCount + 1);
    }
}
