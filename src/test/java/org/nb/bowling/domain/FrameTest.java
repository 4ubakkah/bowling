package org.nb.bowling.domain;

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
        frame.setPinsHitCountFirstTake(1L);
        frame.setPinsHitCountSecondTake(1L);
        assertThat(frame.isFrameComplete()).isTrue();
    }

}