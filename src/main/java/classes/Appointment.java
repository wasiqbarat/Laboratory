package classes;

import javax.swing.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Appointment {
    private Patient patient;
    private String doctor;
    private String description;
    private ArrayList<Test> tests;
    private LocalDateTime appointmentDate;
    private double totalFee;

    private String sex;
    private String ageUnit;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public Appointment(Patient patient, String doctor, ArrayList<Test> tests) {
        this.patient = patient;
        this.doctor = doctor;
        this.tests = tests;
        appointmentDate = LocalDateTime.now();
    }

    public Appointment(Patient patient, String doctor) {
        this.patient = patient;
        this.doctor = doctor;
        tests = new ArrayList<>();
        appointmentDate = LocalDateTime.now();
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        StringBuilder testList = new StringBuilder();
        int counter = 1;
        for (Test test : tests) {
            testList.append(counter).append(". ").append(test.getName()).append("\n");
            counter++;
        }

        return "patient: ( " + patient.toString() +" )"+
                "\nDoctor: " + doctor +
                "\nRegister Date(تاریخ ثبت مریض): " + appointmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd | HH:mm a")) +
                "\nTests :\n" + testList ;
    }
}
