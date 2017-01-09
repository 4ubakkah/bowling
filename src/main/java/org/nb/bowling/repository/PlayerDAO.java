package org.nb.bowling.repository;

import org.nb.bowling.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDAO extends CrudRepository<Player, Long>{

    Player findByUserName(String userName);

}
