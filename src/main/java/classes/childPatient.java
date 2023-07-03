package classes;

public class childPatient extends Patient{

    public childPatient(String firstName, String lastName, int contact, String address, int age, int ID) {
        super(firstName, lastName, contact, address, age, patientCount + 1);
    }
}
