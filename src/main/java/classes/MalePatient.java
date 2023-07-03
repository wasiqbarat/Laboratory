package classes;

public class MalePatient extends Patient{

    public MalePatient(String firstName, String lastName, int contact, String address, int age, int ID) {
        super(firstName, lastName, contact, address, age, patientCount + 1);
    }
}
