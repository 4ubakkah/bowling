package org.nb.bowling.service.impl;

import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;
import org.nb.bowling.dto.GameDTO;
import org.nb.bowling.repository.GameDAO;
import org.nb.bowling.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameDAO gameDao;


    @Autowired
    public GameServiceImpl(GameDAO gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public Game createNewGame(Player player) {
        Game game = new Game();
        game.setGameStatus(Status.CREATED);
        game.setPlayer(player);
        game.setCreated(new Date());
        gameDao.save(game);

        return game;
    }

    @Override
    public void deleteGame(Long gameId) {
        gameDao.delete(gameId);
    }

    @Override
    public Game updateGameStatus(Game game, Status gameStatus) {
        game.setGameStatus(gameStatus);

        gameDao.save(game);
        return game;
    }

    @Override
    public List<Game> getGamesToJoin(Player player) {
        List<Game> games = gameDao.findMultipleByPlayer(player);
        return games.stream()
                .filter(game -> !game.getGameStatus().name().equals(Status.FINISHED.name()))
                .collect(Collectors.toList());

    }

    @Override
    public Game joinGame(Long id) {
        Game game =  getGame(id);
        updateGameStatus(game, Status.IN_PROGRESS);
        return game;
    }

    @Override
    public List<Game> getPlayersFinishedGames(Player player) {
        List<Game> games = gameDao.findMultipleByPlayer(player);
        return games.stream()
                .filter(game -> game.getGameStatus().name().equals(Status.FINISHED.name()))
                .collect(Collectors.toList());
    }



    public Game getGame(Long id) {
        return gameDao.findOne(id);
    }
}