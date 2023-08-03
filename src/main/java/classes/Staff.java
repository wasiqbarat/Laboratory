package classes;

public class Staff extends Person{
    protected static final int staffCount = 0;
    protected String job;
    protected String salary;

    public Staff(String firstName, String lastName, String fatherName, int contact, String address, int age) {
        super(firstName, lastName, fatherName, contact, address, age, staffCount + 1);
    }
    public Staff(String firstName, String lastName, String fatherName, int contact, String address, int age, String job, String nationalID, String salary) {
        super(firstName, lastName, fatherName, contact, address, age, staffCount + 1);
        this.salary = salary;
        this.job = job;
        this.setNationalID(nationalID);
    }


}
