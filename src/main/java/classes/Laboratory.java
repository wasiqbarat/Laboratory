package classes;

import java.util.ArrayList;

public class Laboratory {
    private String name;
    private Person owner;

    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staff;
    private ArrayList<Test> tests;


    protected String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    public ArrayList<Staff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

}
