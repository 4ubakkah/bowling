package org.nb.bowling.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.service.GameService;
import org.nb.bowling.service.PlayerService;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {


    @InjectMocks
    private GameController controller;

    @Mock
    private GameService gameService;

    @Mock
    private PlayerService playerService;

    @Mock
    private HttpSession httpSession;

    @Test
    public void createNewGame() throws Exception {
        Player player = Mockito.mock(Player.class);
        Game game = Mockito.mock(Game.class);
        Mockito.when(playerService.getLoggedUser()).thenReturn(player);
        Mockito.when(gameService.createNewGame(Mockito.any())).thenReturn(game);

        Game createdGame = controller.createNewGame();

        Mockito.verify(playerService).getLoggedUser();
        Mockito.verify(gameService).createNewGame(Mockito.eq(player));
        Mockito.verify(httpSession).setAttribute("gameId", game.getId());
        assertThat(createdGame).isEqualTo(game);
    }

    @Test
    public void getGamesToJoin() throws Exception {
        Player player = Mockito.mock(Player.class);
        List<Game> games = new ArrayList<>();
        Mockito.when(playerService.getLoggedUser()).thenReturn(player);

        List<Game> retrievedGames = controller.getGamesToJoin();

        Mockito.verify(playerService).getLoggedUser();
        Mockito.verify(gameService).getGamesToJoin(player);
        assertThat(retrievedGames).isEqualTo(games);
    }

    @Test
    public void joinGame() throws Exception {
        Game game = Mockito.mock(Game.class);
        Long gameId = 1L;
        Mockito.when(gameService.joinGame(Mockito.eq(gameId))).thenReturn(game);

        Game retrievedGame = controller.joinGame(gameId);

        Mockito.verify(gameService).joinGame(gameId);
        assertThat(retrievedGame).isEqualTo(game);
    }

    @Test
    public void getPlayerFinishedGames() throws Exception {
        Player player = Mockito.mock(Player.class);
        List<Game> games = new ArrayList<>();
        Mockito.when(playerService.getLoggedUser()).thenReturn(player);

        List<Game> retrievedGames = controller.getPlayersFinishedGames();

        Mockito.verify(playerService).getLoggedUser();
        Mockito.verify(gameService).getPlayersFinishedGames(player);
        assertThat(retrievedGames).isEqualTo(games);
    }

    @Test
    public void getGameProperties() throws Exception {
        Long gameId = 1L;
        Game game = Mockito.mock(Game.class);
        Mockito.when(gameService.getGame(gameId)).thenReturn(game);

        Game retrievedGame = controller.getGameProperties(gameId);

        Mockito.verify(httpSession).setAttribute("gameId", gameId);
        Mockito.verify(gameService).getGame(gameId);
        assertThat(retrievedGame).isEqualTo(game);
    }

    @Test
    public void deleteGame() throws Exception {
        Long gameId = 1L;

        controller.deleteGame(gameId);

        Mockito.verify(gameService).deleteGame(gameId);
    }

}