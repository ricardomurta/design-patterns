package behavioral.state;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum StateSM {
    OFF_HOOK,
    ON_HOOK,
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum TriggerSM {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

class CustomPair<TriggerSM, StateSM> {
    private TriggerSM key;
    private StateSM value;

    public CustomPair(TriggerSM key, StateSM value) {
        this.key = key;
        this.value = value;
    }

    public TriggerSM getKey() {
        return key;
    }

    public void setKey(TriggerSM key) {
        this.key = key;
    }

    public StateSM getValue() {
        return value;
    }

    public void setValue(StateSM value) {
        this.value = value;
    }
}

class DemoSM {
    private static Map<StateSM, List<CustomPair<TriggerSM, StateSM>>> rules = new HashMap<>();

    static {
        rules.put(StateSM.OFF_HOOK, List.of(
                new CustomPair<>(TriggerSM.CALL_DIALED, StateSM.CONNECTING),
                new CustomPair<>(TriggerSM.STOP_USING_PHONE, StateSM.ON_HOOK)
        ));
    }

    private static  StateSM currentState = StateSM.OFF_HOOK;
    private static StateSM exitState = StateSM.ON_HOOK;

    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in)
        );
        while (true) {
            System.out.println("The phone is currently " + currentState);
            System.out.println("Select a trigger");
            for (int i = 0; i < rules.get(currentState).size(); i++) {
                TriggerSM trigger = rules.get(currentState).get(i).getKey();
                System.out.println("" + i + ". " + trigger);
            }

            boolean parseOk;
            int choice = 0;

            do {
                try {
                    System.out.println("Please enter your choice:");
                    choice = Integer.parseInt(console.readLine());
                    parseOk = choice >= 0 && choice < rules.get(currentState).size();
                } catch (IOException e) {
                    parseOk = false;
                }
            } while (!parseOk);

            currentState = rules.get(currentState).get(choice).getValue();
            if (currentState == exitState) break;
        }
        System.out.println("And we are done!");
    }
}