package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Appointment {
    private Patient patient;
    private String doctor;
    private String description;
    private ArrayList<Test> tests;
    private LocalDateTime appointmentDate;
    private double totalFee;

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
        return "Appointment{" +
                "patient=" + patient +
                ", doctor='" + doctor + '\'' +
                ", description='" + description + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", tests=" + tests +
                '}';
    }
}
