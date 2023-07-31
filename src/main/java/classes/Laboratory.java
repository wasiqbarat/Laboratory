package classes;

import java.util.ArrayList;

public class Laboratory {
    private String name;
    private Person owner;

    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    private ArrayList<Staff> staff;
    private ArrayList<Test> tests;

    public Laboratory() {
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
        staff = new ArrayList<>();
        tests = new ArrayList<>();
        testCreation();
    }


    private void testCreation() {
        //1
        Test Brucellosis = new Test("BRUCELLOSIS", 200);
        TestParameter brucelosisAbortus = new InRangeTestParameter("BRUCELOSIS Abortus","1:20","", "1:80");
        TestParameter brucelosisMelitensis = new InRangeTestParameter("BRUCELOSIS Melitensis","1:20","", "1:80");
        Brucellosis.addParameter(brucelosisAbortus);
        Brucellosis.addParameter(brucelosisMelitensis);
        tests.add(Brucellosis);

        //2
        Test aptt = new Test("ACTIVATED PARCIAL THROMBIN TIM", 400);
        TestParameter pt = new InRangeTestParameter("PT (PATIENT)", "", "Sec", "");
        TestParameter ptC = new InRangeTestParameter("PT CONTROL", "14", "Sec", "");
        TestParameter INR = new InRangeTestParameter("INR", "", "", "");
        aptt.addParameter(pt);
        aptt.addParameter(ptC);
        aptt.addParameter(INR);
        tests.add(aptt);

        //3

    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addStaff(Staff staff1) {
        staff.add(staff1);
    }

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
