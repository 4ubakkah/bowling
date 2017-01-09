package org.nb.bowling.repository;

import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDAO extends CrudRepository<Game, Long> {

    //TODO implement new user registration
    //Game findByPlayer(Player player);

    List<Game> findMultipleByPlayer(Player player);

    //TODO refactor code to retrieve games by player and status code
    //List<Game> findByGameStatusAndPlayer(Status status, Player player);
}
