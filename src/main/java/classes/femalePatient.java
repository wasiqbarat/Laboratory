package classes;

public class femalePatient extends Patient {

    public femalePatient(String firstName, String lastName, int contact, String address, int age, int ID) {
        super(firstName, lastName, contact, address, age, patientCount + 1);
    }
}
