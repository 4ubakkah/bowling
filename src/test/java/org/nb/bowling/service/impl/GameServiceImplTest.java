package org.nb.bowling.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.GameFixture;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;
import org.nb.bowling.repository.GameDAO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl service;

    @Mock
    private GameDAO gameDao;

    @Test
    public void createNewGame() throws Exception {
        Player player = new Player();

        Game persistedGame = service.createNewGame(player);

        Mockito.verify(gameDao).save(Mockito.any(Game.class));
        assertThat(persistedGame.getGameStatus()).isEqualTo(Status.CREATED);
        assertThat(persistedGame.getPlayer()).isEqualTo(player);
        assertThat(persistedGame.getCreated()).isNotNull();
    }



    @Test
    public void deleteGame() throws Exception {
        Long gameId = 1L;

        service.deleteGame(gameId);

        Mockito.verify(gameDao).delete(gameId);
    }

    @Test
    public void updateGameStatus() throws Exception {
        Game game = new Game();
        Status status = Status.IN_PROGRESS;

        service.updateGameStatus(game, status);

        Mockito.verify(gameDao).save(game);
        assertThat(game.getGameStatus()).isEqualTo(status);
    }

    @Test
    public void getGamesToJoin() throws Exception {
        Player player = new Player();
        List<Game> games = new ArrayList<>();
        games.add(GameFixture.aFinishedGame());
        games.add(GameFixture.aGameInProgress());
        Mockito.when(gameDao.findMultipleByPlayer(player)).thenReturn(games);

        List<Game> retrievedGames = service.getGamesToJoin(player);

        Mockito.verify(gameDao).findMultipleByPlayer(player);
        assertThat(retrievedGames).extracting(Game::getGameStatus).doesNotContain(Status.FINISHED);
    }

    @Test
    public void joinGame() throws Exception {
        Long gameId = 1l;
        Game game = new Game();
        Mockito.when(gameDao.findOne(gameId)).thenReturn(game);

        Game retrievedGame = service.joinGame(gameId);

        Mockito.verify(gameDao).save(game);
        assertThat(retrievedGame.getGameStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    @Test
    public void getPlayersFinishedGames() throws Exception {
        Player player = new Player();
        List<Game> games = new ArrayList<>();
        games.add(GameFixture.aFinishedGame());
        games.add(GameFixture.aGameInProgress());
        Mockito.when(gameDao.findMultipleByPlayer(player)).thenReturn(games);

        List<Game> retrievedGames = service.getPlayersFinishedGames(player);

        Mockito.verify(gameDao).findMultipleByPlayer(player);
        assertThat(retrievedGames).extracting(Game::getGameStatus).containsOnly(Status.FINISHED);
    }


    @Test
    public void getGame() throws Exception {
        Long gameId = 1L;
        Game game = GameFixture.aGameInProgress();
        Mockito.when(gameDao.findOne(gameId)).thenReturn(game);

        Game retreivedGame = service.getGame(gameId);

        Mockito.verify(gameDao).findOne(gameId);
        assertThat(retreivedGame).isEqualTo(game);
    }

}