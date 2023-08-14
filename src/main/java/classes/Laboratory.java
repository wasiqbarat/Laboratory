package classes;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

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
        doctorCreation();
    }


    private void testCreation() {
   /*     //1
        Test Brucellosis = new Test("BRUCELLOSIS", 200);
        TestParameter brucelosisAbortus = new InRangeTestParameter("BRUCELOSIS Abortus","","1:80","1:20","BRUCELLOSIS", 200);
        TestParameter brucelosisMelitensis = new InRangeTestParameter("BRUCELOSIS Melitensis","","1:80", "1:20","BRUCELLOSIS", 200);
        Brucellosis.addParameter(brucelosisAbortus);
        Brucellosis.addParameter(brucelosisMelitensis);

        tests.add(Brucellosis);

        //2
        Test aptt = new Test("ACTIVATED PARCIAL THROMBIN TIM", 400);
        TestParameter pt = new InRangeTestParameter("PT (PATIENT)", "Sec", "", "", "ACTIVATED PARCIAL THROMBIN TIM", 400);
        TestParameter ptC = new InRangeTestParameter("PT CONTROL", "Sec", "", "14", "ACTIVATED PARCIAL THROMBIN TIM", 400);
        TestParameter INR = new InRangeTestParameter("INR", "", "", "", "ACTIVATED PARCIAL THROMBIN TIM", 400);
        aptt.addParameter(pt);
        aptt.addParameter(ptC);
        aptt.addParameter(INR);
        tests.add(aptt);*/

        //1
        Test Brucellosis = new Test("BRUCELLOSIS", 200);
        TestParameter brucelosisAbortus = new InRangeTestParameter(new SimpleStringProperty("BRUCELOSIS Abortus"), new SimpleStringProperty(""), new SimpleStringProperty("1:80"), new SimpleStringProperty("1:20"), new SimpleStringProperty("BRUCELLOSIS"), new SimpleDoubleProperty(200) );
        TestParameter brucelosisMelitensis = new InRangeTestParameter(new SimpleStringProperty("BRUCELOSIS Melitensis"),new SimpleStringProperty(""),new SimpleStringProperty("1:80"),new SimpleStringProperty( "1:20"),new SimpleStringProperty("BRUCELLOSIS"), new SimpleDoubleProperty(200));
        Brucellosis.addParameter(brucelosisAbortus);
        Brucellosis.addParameter(brucelosisMelitensis);

        tests.add(Brucellosis);

        //2
        Test aptt = new Test("ACTIVATED PARCIAL THROMBIN TIM", 400);
        TestParameter pt = new InRangeTestParameter(new SimpleStringProperty("PT (PATIENT)"), new SimpleStringProperty("Sec"), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty("ACTIVATED PARCIAL THROMBIN TIM"), new SimpleDoubleProperty(400));
        TestParameter ptC = new InRangeTestParameter(new SimpleStringProperty("PT CONTROL"), new SimpleStringProperty("Sec"), new SimpleStringProperty(""), new SimpleStringProperty("14"), new SimpleStringProperty("ACTIVATED PARCIAL THROMBIN TIM"), new SimpleDoubleProperty(400));
        TestParameter INR = new InRangeTestParameter(new SimpleStringProperty("INR"),new SimpleStringProperty( ""), new SimpleStringProperty(""), new SimpleStringProperty(""), new SimpleStringProperty("ACTIVATED PARCIAL THROMBIN TIM"), new SimpleDoubleProperty(400));
        aptt.addParameter(pt);
        aptt.addParameter(ptC);
        aptt.addParameter(INR);
        tests.add(aptt);

        //3

    }

    private void doctorCreation() {
        Doctor doctor1 = new Doctor("Alimohammad", "Samadi", "Dost mohammad", 1234, "ghor", 45, "p034");
        doctors.add(doctor1);

        Doctor doctor2 = new Doctor("GulAhmad", "Seadi", "Mohammad", 1234, "ghor", 45, "p034");
        doctors.add(doctor2);
    }


    public Test getTest(String testName) {
        Test test = new Test("no test", 0);
        for (Test t : tests) {
            if (t.getName().equals(testName)) {
                test = t;
            }
        }

        return test;
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
