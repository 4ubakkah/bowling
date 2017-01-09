package org.nb.bowling.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.nb.bowling.domain.Player;
import org.nb.bowling.dto.PlayerDTO;
import org.nb.bowling.repository.PlayerDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl service;

    @Mock
    private PlayerDAO playerDao;

    @Test
    public void createNewPlayer() throws Exception {
        PlayerDTO dto = new PlayerDTO();
        dto.setUserName("userName");
        dto.setPassword("password");
        dto.setEmail("email");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Player retrievedPlayer = service.createNewPlayer(dto);

        Mockito.verify(playerDao).save(retrievedPlayer);
        assertThat(retrievedPlayer.getEmail()).isEqualTo(dto.getEmail());
        assertThat(passwordEncoder.matches(dto.getPassword(), retrievedPlayer.getPassword())).isTrue();
        assertThat(retrievedPlayer.getUserName()).isEqualTo(dto.getUserName());
    }

    @Test
    @Ignore(value = "Implement with stubbed authentification.")
    public void getLoggedUser() throws Exception {
    }

    @Test
    public void listPlayers() throws Exception {
        List<Player> players = new ArrayList<>();
        Mockito.when(playerDao.findAll()).thenReturn(players);

        List<Player> retrievedPlayers = service.listPlayers();

        Mockito.verify(playerDao).findAll();
        assertThat(retrievedPlayers).isEqualTo(players);
    }

}