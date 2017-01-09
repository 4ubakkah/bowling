package org.nb.bowling.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.nb.bowling.domain.enums.Status;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "Game")
public class Game {

    public static final int FRAMES_COUNT_IN_ONE_GAME = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_status", nullable = false)
    private Status gameStatus;

    @Column(name = "created", nullable = false)
    private Date created;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonManagedReference
    @OrderBy
    private Set<Frame> frames = new TreeSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Status getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Status gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Frame> getFrames() {
        return frames;
    }

    public void setFrames(Set<Frame> frames) {
        this.frames = frames;
    }

    public Frame getLatestFrame() {
        return frames.size() > 0 ? frames.stream().reduce((a, b) -> b).get() : null;
    }

    public boolean isGameComplete() {
        return frames.size() == FRAMES_COUNT_IN_ONE_GAME && getLatestFrame().isFrameComplete();
    }

    public boolean isGameInProgress() {
        return !isGameComplete() && !frames.isEmpty();
    }
}
