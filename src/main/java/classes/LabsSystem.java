package classes;

import java.util.ArrayList;

public class LabsSystem {
    private ArrayList<Laboratory> laboratories;

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

}
