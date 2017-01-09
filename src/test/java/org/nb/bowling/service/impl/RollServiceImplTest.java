package org.nb.bowling.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.nb.bowling.repository.RollDAO;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RollServiceImplTest {

    @InjectMocks
    private RollServiceImpl service;

    @Mock
    private RollDAO rollDao;

    @Test
    public void createMove() throws Exception {

    }

    @Test
    public void checkCurrentGameStatus() throws Exception {

    }

    @Test
    public void getMovesInGame() throws Exception {

    }

}