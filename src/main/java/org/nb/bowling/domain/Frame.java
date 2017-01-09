package org.nb.bowling.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Frame implements Comparable<Frame> {

    public static final int PINS_COUNT = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private Game game;

    @Column(name = "pins_hit_count_first_take", nullable = false)
    private Long pinsHitCountFirstTake;

    @Column(name = "pins_hit_count_second_take", nullable = false)
    private Long pinsHitCountSecondTake;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getPinsHitCountFirstTake() {
        return pinsHitCountFirstTake;
    }

    public void setPinsHitCountFirstTake(Long pinsHitCountFirstTake) {
        this.pinsHitCountFirstTake = pinsHitCountFirstTake;
    }

    public Long getPinsHitCountSecondTake() {
        return pinsHitCountSecondTake;
    }

    public void setPinsHitCountSecondTake(Long pinsHitCountSecondTake) {
        this.pinsHitCountSecondTake = pinsHitCountSecondTake;
    }

    public boolean isFrameComplete() {
        return pinsHitCountFirstTake != null && pinsHitCountSecondTake != null;
    }

    //TODO avoid sorting by technical id
    @Override
    public int compareTo(Frame o) {

        return Long.compare(this.getId(), o.getId());
    }
}
