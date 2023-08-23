package classes;

import java.time.LocalDateTime;

public class Staff extends Person{
    protected final int ID;
    protected static int staffCount = 0;
    protected String job;
    protected String salary;
    private final LocalDateTime registerDate;

    public Staff(String firstName, String lastName, String fatherName, int contact, String address, int age, int ID) {
        super(firstName, lastName, fatherName, contact, address, age);
        staffCount++;
        this.ID = ID;
        registerDate = LocalDateTime.now();
    }

    public Staff(String firstName, String lastName, String fatherName, int contact, String address, int age, String job, String nationalID, String salary, int ID) {
        super(firstName, lastName, fatherName, contact, address, age);
        this.salary = salary;
        this.job = job;
        this.setNationalID(nationalID);
        registerDate = LocalDateTime.now();
        this.ID = ID;
        staffCount++;
    }
}
