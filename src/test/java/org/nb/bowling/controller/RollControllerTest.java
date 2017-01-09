package org.nb.bowling.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;
import org.nb.bowling.dto.GameDTO;
import org.nb.bowling.service.GameService;
import org.nb.bowling.service.PlayerService;
import org.nb.bowling.service.RollService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RollControllerTest {

    @InjectMocks
    private RollController controller;

    @Mock
    private PlayerService playerService;

    @Mock
    private RollService rollService;

    @Mock
    private GameService gameService;

    @Mock
    private HttpSession httpSession;

    @Test
    public void createMove() throws Exception {
        Long gameId = 1L;
        Game game = new Game();
        Player player = new Player();
        Frame frame = new Frame();
        frame.setId(1L);
        game.getFrames().add(frame);
        Status status = Status.CREATED;
        when(httpSession.getAttribute("gameId")).thenReturn(gameId);
        when(gameService.getGame(gameId)).thenReturn(game);
        when(playerService.getLoggedUser()).thenReturn(player);
        when(rollService.createMove(game, player)).thenReturn(frame);
        when(rollService.checkCurrentGameStatus(game)).thenReturn(status);

        GameDTO gameDto = controller.createMove();

        verify(gameService).updateGameStatus(game, status);

        assertThat(gameDto.getCurrentGame()).isEqualTo(game);
        assertThat(gameDto.getLatestFrame()).isEqualTo(game.getLatestFrame());
    }

    @Test
    public void getMovesInGame() throws Exception {
        Long gameId = 1L;
        List<Frame> frames = new ArrayList<>();
        Game game = new Game();

        when(httpSession.getAttribute("gameId")).thenReturn(gameId);
        when(gameService.getGame(gameId)).thenReturn(game);
        when(rollService.getMovesInGame(game)).thenReturn(frames);

        List<Frame> retrievedFrames = controller.getMovesInGame();
        assertThat(retrievedFrames).isEqualTo(frames);
    }

}