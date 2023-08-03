package classes;

import java.time.LocalDateTime;

public class Person {
    protected String firstName;
    private String lastName;
    private String fatherName;
    private String address;
    private int contact;
    private int age;
    private int ID;
    private LocalDateTime registerData;
    private String nationalID;

    public Person(String firstName, String lastName, String fatherName, int contact, String address, int age, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.ID = ID;
        this.fatherName = fatherName;
        registerData = LocalDateTime.now();
    }

    public LocalDateTime getRegisterData() {
        return registerData;
    }

    public void setRegisterData(LocalDateTime registerData) {
        this.registerData = registerData;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }
}
