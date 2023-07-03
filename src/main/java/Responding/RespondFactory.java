package Responding;

public class RespondFactory {
    private static RespondFactory respondFactory = null;

    private RespondFactory(){
    }

    public static RespondFactory getInstance() {
        if (respondFactory == null) {
            respondFactory = new RespondFactory();
        }
        return respondFactory;
    }


    public void getRespond() {
        return;
    }

}
