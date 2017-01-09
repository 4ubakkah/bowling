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
            frame.setPinsHitCountFirstTake(1);
            frame.setPinsHitCountSecondTake(1);
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
            frame.setPinsHitCountFirstTake(1);
            frame.setPinsHitCountSecondTake(1);
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
            frame.setPinsHitCountFirstTake(1);
            frame.setPinsHitCountSecondTake(1);
            game.getFrames().add(frame);
        }

        assertThat(game.isGameComplete()).isTrue();
    }

    @Test
    public void getGameScore_returnsTotalScore() {
        Game game = new Game();

        Frame frame1 = new Frame();
        frame1.setPinsHitCountSecondTake(9);

        Frame frame2 = new Frame();
        frame2.setPinsHitCountSecondTake(2);

        assertThat(game.getGameScore()).isEqualTo(frame1.getScore() + frame2.getScore());
    }

}