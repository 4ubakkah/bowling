package org.nb.bowling.service;

import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;

import java.util.List;


public interface RollService {

    Frame createMove(Game game, Player player);

    Status checkCurrentGameStatus(Game game);

    List<Frame> getMovesInGame(Game game);

}
