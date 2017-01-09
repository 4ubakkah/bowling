package org.nb.bowling.dto;

import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;

public class GameDTO {

    private Long id;
    private Frame latestFrame;
    private Game currentGame;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Frame getLatestFrame() {
        return latestFrame;
    }

    public void setLatestFrame(Frame latestFrame) {
        this.latestFrame = latestFrame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }
}
