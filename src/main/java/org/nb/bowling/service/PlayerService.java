package org.nb.bowling.service;

import org.nb.bowling.domain.Player;
import org.nb.bowling.dto.PlayerDTO;

import java.util.List;

public interface PlayerService {

    //TODO implement new user registration
    Player createNewPlayer(PlayerDTO playerDTO);

    Player getLoggedUser();

    //TODO implement new user registration
    List<Player> listPlayers();
}
