package org.nb.bowling.controller;


import org.nb.bowling.domain.Game;
import org.nb.bowling.service.GameService;
import org.nb.bowling.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    PlayerService playerService;

    @Autowired
    HttpSession httpSession;

    Logger logger = LoggerFactory.getLogger(GameController.class);


    @RequestMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Game createNewGame() {

        Game game = gameService.createNewGame(playerService.getLoggedUser());
        httpSession.setAttribute("gameId", game.getId());

        logger.info("new game id: " + httpSession.getAttribute("gameId")+ " stored in session" );

        return game;
    }

    @RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getGamesToJoin() {
        return gameService.getGamesToJoin(playerService.getLoggedUser());
    }

    @RequestMapping(value = "/join/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Game joinGame(@PathVariable("gameId") Long id) {
        Game game = gameService.joinGame(id);
        return game;
    }


    @RequestMapping(value = "/player/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Game> getPlayersFinishedGames() {
        return gameService.getPlayersFinishedGames(playerService.getLoggedUser());
    }

    @RequestMapping(value = "/{id}")
    public Game getGameProperties(@PathVariable Long id) {

        httpSession.setAttribute("gameId", id);

        return gameService.getGame(id);
    }

    @RequestMapping(value = "/delete/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGame(@PathVariable("gameId") Long id) {
        gameService.deleteGame(id);
    }

}