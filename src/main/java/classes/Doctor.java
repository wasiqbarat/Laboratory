package classes;

import java.time.LocalDateTime;

public class Doctor extends Person {

    private final int ID;
    private final LocalDateTime registerData;

    public Doctor(String firstName, String lastName, String fatherName, int contact, String address, int age, int id) {
        super(firstName, lastName, fatherName, contact, address, age);
        ID = id;
        registerData = LocalDateTime.now();
    }
    public Doctor(String firstName, String lastName, String fatherName, int contact, String address, int age, String nationalID, int id) {
        super(firstName, lastName, fatherName, contact, address, age);
        ID = id;
        this.setNationalID(nationalID);
        registerData = LocalDateTime.now();

    }

    @Override
    public String toString() {
        return "Name: " + getFirstName() + " " + getLastName();
    }
}
