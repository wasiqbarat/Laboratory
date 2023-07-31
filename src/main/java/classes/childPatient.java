package classes;

public class childPatient extends Patient{

    public childPatient(String firstName, String lastName, String fatherName, int contact, String address, int age) {
        super(firstName, lastName, fatherName, contact, address, age, patientCount + 1);
    }

}
