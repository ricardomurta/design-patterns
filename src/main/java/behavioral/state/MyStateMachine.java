package behavioral.state;

import java.util.HashMap;
import java.util.Map;

enum States {
    PROCESSING,
    WAITING_PAYENT,
    IN_PROGRESS,
    COMPLETED
}

enum Events {
    ORDER_CREATED,
    INVOICE_CREATED,
    INVOICE_PAID,
    DELIVERED
}

abstract class StateAbstract {
    public States state;
    public Map<Events, StateAbstract> transitions = new HashMap<>();

    public StateAbstract getNextBasedOnEvent(Events event) {
        return this.transitions.get(event);
    }

    @Override
    public String toString() {
        return "State{" +
                "state=" + state +
                '}';
    }
}

class ProcessingState extends StateAbstract {
    public ProcessingState() {
        this.state = States.PROCESSING;
        System.out.println("State switched to: " + this.state);
        addTransitions();
    }

    void addTransitions() {
        this.transitions.put(Events.INVOICE_CREATED, new WaitingPaymentState());
    }
}

class WaitingPaymentState extends StateAbstract {
    public WaitingPaymentState() {
        this.state = States.WAITING_PAYENT;
        System.out.println("State switched to: " + this.state);
        addTransitions();
    }

    void addTransitions() {
        this.transitions.put(Events.INVOICE_PAID, new InProgressState());
    }
}

class InProgressState extends StateAbstract {
    public InProgressState() {
        this.state = States.IN_PROGRESS;
        System.out.println("State switched to: " + this.state);
        addTransitions();
    }

    void addTransitions() {
        this.transitions.put(Events.DELIVERED, new CompletedState());
    }
}

class CompletedState extends StateAbstract {
    public CompletedState() {
        this.state = States.COMPLETED;
        System.out.println("State switched to: " + this.state);
    }
}

class DemoStateMachine {
    public static void main(String[] args) {
        StateAbstract s = new ProcessingState();
        System.out.println("------------------");
        System.out.println(s);
        s = s.getNextBasedOnEvent(Events.INVOICE_CREATED);
        System.out.println(s);
        s = s.getNextBasedOnEvent(Events.INVOICE_PAID);
        System.out.println(s);
        s = s.getNextBasedOnEvent(Events.DELIVERED);
        System.out.println(s);
    }
}