package classes;

public class MalePatient extends Patient{

    public MalePatient(String firstName, String lastName, String fatherName, int contact, String address, int age) {
        super(firstName, lastName, fatherName, contact, address, age, patientCount + 1);
    }
}
