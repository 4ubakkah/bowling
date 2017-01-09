package org.nb.bowling.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GameDAOITest {

    @Autowired
    private GameDAO gameDao;

    @Autowired
    private PlayerDAO playerDao;

    @Test
    public void shouldFindMultipleByPlayer() throws Exception {
        Player player = playerDao.findOne(1L);

        List<Game> retrievedGames = gameDao.findMultipleByPlayer(player);

        assertThat(retrievedGames).extracting(Game::getId).containsOnly(1L, 2L, 3L);
    }

}