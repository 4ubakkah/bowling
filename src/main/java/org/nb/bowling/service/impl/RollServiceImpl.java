package org.nb.bowling.service.impl;

import org.nb.bowling.domain.Frame;
import org.nb.bowling.domain.Game;
import org.nb.bowling.domain.Player;
import org.nb.bowling.domain.enums.Status;
import org.nb.bowling.dto.RollDTO;
import org.nb.bowling.repository.GameDAO;
import org.nb.bowling.repository.RollDAO;
import org.nb.bowling.service.RollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class RollServiceImpl implements RollService {

    @Autowired
    private RollDAO rollDao;

    @Autowired
    private GameDAO gameDao;

    //TODO refactor method for readability and maintainability
    @Override
    public Frame createMove(Game game, Player player) {
        Frame currentFrame = null;

        if (game.getFrames().isEmpty()) {
             createNewFrame(game);
        } else {
            Frame latestFrame = game.getLatestFrame();
            if (!latestFrame.isFrameComplete()) {
                latestFrame.setPinsHitCountSecondTake(randomizeRoll(latestFrame));
                currentFrame = latestFrame;
            } else {
                createNewFrame(game);
            }
        }

        gameDao.save(game);

        return currentFrame;
    }

    private Frame createNewFrame(Game game) {
        Frame newFrame = new Frame();
        newFrame.setPinsHitCountFirstTake(randomizeRoll(newFrame));
        newFrame.setGame(game);
        game.getFrames().add(newFrame);
        return newFrame;
    }

    private Integer randomizeRoll(Frame frame) {
        Integer hitPinsCount = frame.getPinsHitCountFirstTake() != null ? frame.getPinsHitCountFirstTake() : 0;
        return new Random().ints(0, Frame.PINS_COUNT + 1 - hitPinsCount).findFirst().getAsInt();
    }

    @Override
    public Status checkCurrentGameStatus(Game game) {
        if (game.isGameComplete()) {
            return Status.FINISHED;
        } else if (game.isGameInProgress()) {
            return Status.IN_PROGRESS;
        }

        return Status.CREATED;
    }

    @Override
    public List<Frame> getMovesInGame(Game game) {
        return rollDao.findByGame(game);
    }
}
