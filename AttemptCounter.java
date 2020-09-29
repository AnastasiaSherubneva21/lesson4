package lesson4;

public class AttemptCounter {

    private static AttemptCounter instance;

    public static synchronized AttemptCounter getInstance() {
        if (instance == null) {
            instance = new AttemptCounter();
        }
        return instance;
    }


}
