package classes;

import java.util.ArrayList;

public class LabsSystem {
    private static LabsSystem labsSystem = null;

    private ArrayList<Laboratory> laboratories;
    private ArrayList<Role> roles;
    private ArrayList<User> users;

    public LabsSystem() {
        users = new ArrayList<>();
        laboratories = new ArrayList<>();
        roles = new ArrayList<>();
        ArrayList<Ability> ownerAbilities = new ArrayList<>();
        ownerAbilities.add(Ability.ADD_DOCTOR);
        ownerAbilities.add(Ability.REMOVE_DOCTOR);
        ownerAbilities.add(Ability.ADD_PATIENT);
        ownerAbilities.add(Ability.REMOVE_PATIENT);
        ownerAbilities.add(Ability.ADD_TEST);
        ownerAbilities.add(Ability.REMOVE_TEST);
        ownerAbilities.add(Ability.ADD_STAFF);
        ownerAbilities.add(Ability.REMOVE_STAFF);

        Role ownerRole = new Role("owner", ownerAbilities);

        ArrayList<Ability> staffAbilities = new ArrayList<>();
        staffAbilities.add(Ability.ADD_PATIENT);
        staffAbilities.add(Ability.REMOVE_PATIENT);
        Role staffRole = new Role("staff", staffAbilities);

        // add user by default
        // user should change password after first login
        User user = new User("admin", "admin", ownerRole);
        users.add(user);
    }

    public static LabsSystem getInstance() {
        if (labsSystem == null) {
            labsSystem = new LabsSystem();
        }
        return labsSystem;
    }

    public boolean authentication(String userName, String password) {
        for (User user: users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public Laboratory getLaboratory(String name) {
        for (Laboratory laboratory : laboratories) {
            if (laboratory.getName().equals(name)) {
                return laboratory;
            }
        }
        return null;
    }

    public void addLaboratory(Laboratory laboratory) {
        laboratories.add(laboratory);
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

}