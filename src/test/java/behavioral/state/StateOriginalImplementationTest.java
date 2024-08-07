package behavioral.state;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateOriginalImplementationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenOffState_whenTurnOn_thenSwitchToOnState() {
        State expectedState = new OnState();

        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();

        assertEquals(expectedState.getClass(), lightSwitch.getState().getClass());
    }

    @Test
    void givenOffState_whenTurnOff_thenThrowError() {
        RuntimeException expectedException = new RuntimeException();

        LightSwitch lightSwitch = new LightSwitch();

        assertThrowsExactly(expectedException.getClass(), () -> lightSwitch.off(), "Light is already off.");
    }

    @Test
    void givenOnState_whenTurnOff_thenSwitchToOffState() {
        State expectedState = new OffState();

        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();
        lightSwitch.off();

        assertEquals(expectedState.getClass(), lightSwitch.getState().getClass());
    }

    @Test
    void givenOnState_whenTurnOn_thenThrowError() {
        RuntimeException expectedException = new RuntimeException();

        LightSwitch lightSwitch = new LightSwitch();
        lightSwitch.on();

        assertThrowsExactly(expectedException.getClass(), () -> lightSwitch.on(), "Light is already on.");
    }
}