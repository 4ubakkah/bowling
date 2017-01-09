package org.nb.bowling.domain;


import org.nb.bowling.domain.enums.Status;

public class GameFixture {

    private GameFixture() {}

    public static Game aFinishedGame() {
        Game game = new Game();
        game.setGameStatus(Status.FINISHED);
        return game;
    }

    public static Game aGameInProgress() {
        Game game = new Game();
        game.setGameStatus(Status.IN_PROGRESS);
        return game;
    }
}
