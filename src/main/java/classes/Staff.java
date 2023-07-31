package classes;

public class Staff extends Person{
    protected static final int staffCount = 0;

    public Staff(String firstName, String lastName, String fatherName, int contact, String address, int age) {
        super(firstName, lastName, fatherName, contact, address, age, staffCount + 1);
    }


}
