package classes;

import java.util.ArrayList;

public class Role {
    private String name;
    private ArrayList<Ability> abilities;
    private ArrayList<String> members;

    public Role(String name, ArrayList<Ability> permissions) {
        this.name = name;
        this.abilities = permissions;
        members = new ArrayList<>();
    }

    public boolean hasAbility(Ability ability) {
        return abilities.contains(ability);
    }

    public String getName() {
        return name;
    }

    public boolean isMember(String userName) {
        return members.contains(userName);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(String userName) {
        members.add(userName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", abilities=" + abilities +
                ", members=" + members +
                '}';
    }
}
