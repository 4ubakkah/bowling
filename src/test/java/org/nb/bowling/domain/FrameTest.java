package org.nb.bowling.domain;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class FrameTest {

    @Test
    public void isFrameComplete_returnsFalse_whenPinsHitAreNotSet() throws Exception {
        Frame frame = new Frame();
        assertThat(frame.isFrameComplete()).isFalse();
    }

    @Test
    public void isFrameComplete() throws Exception {
        Frame frame = new Frame();
        frame.setPinsHitCountFirstTake(1);
        frame.setPinsHitCountSecondTake(1);
        assertThat(frame.isFrameComplete()).isTrue();
    }

    @Test
    public void isMiss_returnsTrue_whenNotBothTakesDidntHitAnyPin() throws Exception {
        Frame frame = new Frame();
        assertThat(frame.isMiss()).isTrue();
    }

    @Test
    public void isMiss_returnsFalse_whenEitherOneTakeDidHitPin() throws Exception {
        Frame frame = new Frame();
        frame.setPinsHitCountFirstTake(1);
        assertThat(frame.isMiss()).isFalse();
    }

    @Test
    public void isStrike_returnsTrue_whenEitherOneTakeDidHitAllPins() throws Exception {
        Frame frame = new Frame();
        frame.setPinsHitCountFirstTake(Frame.PINS_COUNT);
        assertThat(frame.isStrike()).isTrue();
    }

    @Test
    public void isStrike_returnsFalse_whenNoneTakeDidntHitAllPins() throws Exception {
        Frame frame = new Frame();
        assertThat(frame.isStrike()).isFalse();
    }

    @Test
    @Ignore(value = "Re-enable when spare check is implemented")
    public void isSpare_returnsTrue() {
        Frame frame = new Frame();
    }

    @Test
    @Ignore(value = "Re-enable when spare check is implemented")
    public void isSpare_returnsFalse() {
        Frame frame = new Frame();
    }

}