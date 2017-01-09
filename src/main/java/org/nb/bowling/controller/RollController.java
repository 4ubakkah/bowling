package org.nb.bowling.controller;

import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;
import org.nb.bowling.dto.GameDTO;
import org.nb.bowling.service.GameService;
import org.nb.bowling.service.PlayerService;
import org.nb.bowling.service.RollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/move")
public class RollController {

    @Autowired
    private RollService moveService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private HttpSession httpSession;

    Logger logger = LoggerFactory.getLogger(RollController.class);

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public GameDTO createMove() {
        Long gameId = (Long) httpSession.getAttribute("gameId");

        Game game = gameService.getGame(gameId);
        Frame move = moveService.createMove(game, playerService.getLoggedUser());
        gameService.updateGameStatus(game, moveService.checkCurrentGameStatus(game));

        GameDTO dto = new GameDTO();
        dto.setCurrentGame(game);
        dto.setLatestFrame(move);

        dto.setId(game.getId());

        return dto;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Frame> getMovesInGame() {

        Long gameId = (Long) httpSession.getAttribute("gameId");

        return moveService.getMovesInGame(gameService.getGame(gameId));
    }
}
