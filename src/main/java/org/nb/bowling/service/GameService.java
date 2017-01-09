package org.nb.bowling.service;

import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;
import org.nb.bowling.dto.GameDTO;

import java.util.List;

public interface GameService {

    Game getGame(Long id);

    List<Game> getPlayersFinishedGames(Player player);

    Game joinGame(Long gameId);

    List<Game> getGamesToJoin(Player player);

    Game updateGameStatus(Game game, Status gameStatus);

    Game createNewGame(Player player);

    void deleteGame(Long gameId);
}
