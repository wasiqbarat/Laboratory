package classes;

public abstract class Patient extends Person{
    protected final int ID;


    public int getID() {
        return ID;
    }

    public Patient(String firstName, String lastName, String fatherName, int contact, String address, int age, int ID) {
        super(firstName, lastName,fatherName, contact, address, age);
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + ", Father name: " + getFatherName() + ", Patient ID: " + ID;
    }


}
