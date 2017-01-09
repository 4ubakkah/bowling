package org.nb.bowling.service.impl;

import org.nb.bowling.domain.Player;
import org.nb.bowling.dto.PlayerDTO;
import org.nb.bowling.repository.PlayerDAO;
import org.nb.bowling.security.User;
import org.nb.bowling.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDAO playerDao;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public PlayerServiceImpl(PlayerDAO playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player createNewPlayer(PlayerDTO playerDTO) {
        Player newPlayer = new Player();
        newPlayer.setUserName(playerDTO.getUserName());
        newPlayer.setPassword(passwordEncoder.encode(playerDTO.getPassword()));
        newPlayer.setEmail(playerDTO.getEmail());
        playerDao.save(newPlayer);
        return newPlayer;
    }

    @Override
    public Player getLoggedUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return playerDao.findByUserName(principal.getPlayer().getUserName());
    }

    @Override
    public List<Player> listPlayers() {
        List<Player> players = (List<Player>) playerDao.findAll();
        return players;
    }


}
