package org.nb.bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void getLatestFrame() throws Exception {
        Game game = new Game();

        for (int i = 1; i <= Game.FRAMES_COUNT_IN_ONE_GAME; i++) {
            Frame frame = new Frame();
            frame.setId(new Long(i));
            frame.setPinsHitCountFirstTake(1L);
            frame.setPinsHitCountSecondTake(1L);
            game.getFrames().add(frame);
        }

        assertThat(game.getLatestFrame().getId()).isEqualTo(Game.FRAMES_COUNT_IN_ONE_GAME);
    }

    @Test
    public void isGameComplete_returnsFalse_whenLatestFrameIsNotComplete() throws Exception {
        Game game = new Game();

        for (int i = 1; i <= Game.FRAMES_COUNT_IN_ONE_GAME; i++) {
            Frame frame = new Frame();
            frame.setId(new Long(i));
            game.getFrames().add(frame);
        }

        assertThat(game.isGameComplete()).isFalse();
    }

    @Test
    public void isGameComplete_returnsFalse_whenFramesCountIsLessThenRequired() throws Exception {
        Game game = new Game();

        for (int i = 1; i <= Game.FRAMES_COUNT_IN_ONE_GAME - 1; i++) {
            Frame frame = new Frame();
            frame.setId(new Long(i));
            frame.setPinsHitCountFirstTake(1L);
            frame.setPinsHitCountSecondTake(1L);
            game.getFrames().add(frame);
        }

        assertThat(game.isGameComplete()).isFalse();
    }

    @Test
    public void isGameInProgress_returnsTrue_whenRequirementsAreFulfilled() throws Exception {
        Game game = new Game();

        for (int i = 1; i <= Game.FRAMES_COUNT_IN_ONE_GAME; i++) {
            Frame frame = new Frame();
            frame.setId(new Long(i));
            frame.setPinsHitCountFirstTake(1L);
            frame.setPinsHitCountSecondTake(1L);
            game.getFrames().add(frame);
        }

        assertThat(game.isGameComplete()).isTrue();
    }

}