package org.nb.bowling.repository;


import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RollDAO extends CrudRepository<Frame, Long> {

    List<Frame> findByGame(Game game);

}
