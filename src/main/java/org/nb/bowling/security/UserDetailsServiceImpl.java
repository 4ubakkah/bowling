package org.nb.bowling.security;

import org.nb.bowling.domain.Player;
import org.nb.bowling.repository.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

import static org.springframework.util.StringUtils.isEmpty;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final PlayerDAO playerDao;

    @Autowired
    public UserDetailsServiceImpl(PlayerDAO playerDao) {
        this.playerDao = playerDao;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        checkNotNull(username);

        if(isEmpty(username)) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        Player player = playerDao.findByUserName(username);

        if (player == null) {
            throw new UsernameNotFoundException("Player " + username + " doesn't exists");
        }
        return new User(player);
    }
}
