package classes;

public class Staff extends Person{
    protected static final int staffCount = 0;

    public Staff(String firstName, String lastName, int contact, String address, int age, int ID) {
        super(firstName, lastName, contact, address, age, staffCount + 1);
    }

}
