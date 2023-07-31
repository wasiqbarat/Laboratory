package classes;

public class femalePatient extends Patient {

    public femalePatient(String firstName, String lastName, String fatherName, int contact, String address, int age) {
        super(firstName, lastName, fatherName, contact, address, age, patientCount + 1);
    }
}
