package classes;

public enum Ability {
    ADD_STAFF,
    REMOVE_STAFF,
    ADD_PATIENT,
    REMOVE_PATIENT,
    ADD_DOCTOR,
    REMOVE_DOCTOR,
    ADD_TEST,
    REMOVE_TEST,
    ;

    public static Ability getAbility(String ability) {
        switch (ability) {

            case "addStaff" -> {
                return ADD_STAFF;
            }
            case "removeStaff" -> {
                return REMOVE_STAFF;
            }
            case "addDoctor" -> {
                return ADD_DOCTOR;
            }
            case "removeDoctor" -> {
                return REMOVE_DOCTOR;
            }
            case "addPatient" -> {
                return ADD_PATIENT;
            }
            case "removePatient" -> {
                return REMOVE_PATIENT;
            }
            case "addTest" -> {
                return ADD_TEST;
            }
            case "removeTest" -> {
                return REMOVE_TEST;
            }
        }

        return null;
    }
}
