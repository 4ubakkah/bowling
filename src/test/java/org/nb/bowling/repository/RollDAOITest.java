package org.nb.bowling.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RollDAOITest {

    @Autowired
    private RollDAO rollDao;

    @Test
    public void shouldFindByGame() throws Exception {
        Long gameId = 1L;
        Game game = new Game();
        game.setId(gameId);

        List<Frame> retrievedFrames = rollDao.findByGame(game);

        assertThat(retrievedFrames).extracting(Frame::getGame).extracting(Game::getId).containsOnly(gameId);
    }



}
