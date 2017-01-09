package org.nb.bowling.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PlayerDAOITest {

    @Autowired
    private GameDAO gameDao;

    @Autowired
    private PlayerDAO playerDao;

    @Test
    public void shouldFindByUserName() throws Exception {
        Long playerId = 1L;
        Player player = playerDao.findOne(playerId);

        assertThat(player).isNotNull();
        assertThat(player.getId()).isEqualTo(playerId);
    }

}