package lesson4;

import java.util.HashMap;

public class AttemptCounter {

    private static AttemptCounter instance;

    private HashMap<String, Integer> attemptmap;

    public AttemptCounter() {
        attemptmap = new HashMap<String, Integer>();
    }

    public AttemptCounter(AttemptCounter ob) {
        attemptmap = ob.attemptmap;
    }

    public static final AttemptCounter INSTANCE = new AttemptCounter();

    public static synchronized AttemptCounter getInstance() {
        if (instance == null) {
            instance = new AttemptCounter();
        }
        return instance;
    }

    public boolean checkAttempts(String str) {
        if (attemptmap.containsKey(str)) {
            if (attemptmap.get(str) >= 5) {
                return false;
            }
            else {
                Integer v = attemptmap.get(str);
                attemptmap.put(str, v+1);
                return true;
            }
        }
        else {
            attemptmap.put(str, 1);
            return true;
        }
    }
}
