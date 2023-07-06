package controllers;

import classes.LabsSystem;

public class Controller {
    protected LabsSystem labsSystem;

    public Controller() {
        labsSystem = LabsSystem.getInstance();
    }
}
