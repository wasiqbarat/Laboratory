package classes;

import java.time.LocalDateTime;

public class Person {
    private String firstName;
    private String lastName;
    private String address;
    private int contact;
    private int age;
    private int ID;
    private LocalDateTime registerData;

    public Person(String firstName, String lastName, int contact, String address, int age, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.ID = ID;
        registerData = LocalDateTime.now();
    }

    protected LocalDateTime getRegisterData() {
        return registerData;
    }

    protected void setRegisterData(LocalDateTime registerData) {
        this.registerData = registerData;
    }

    protected int getID() {
        return ID;
    }

    protected void setID(int ID) {
        this.ID = ID;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected int getContact() {
        return contact;
    }

    protected void setContact(int contact) {
        this.contact = contact;
    }

    protected String getAddress() {
        return address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }

    protected int getAge() {
        return age;
    }

    protected void setAge(int age) {
        this.age = age;
    }

}
