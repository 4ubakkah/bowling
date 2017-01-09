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
    private Integer pinsHitCountFirstTake;

    @Column(name = "pins_hit_count_second_take", nullable = false)
    private Integer pinsHitCountSecondTake;

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

    public Integer getPinsHitCountFirstTake() {
        return pinsHitCountFirstTake;
    }

    public void setPinsHitCountFirstTake(Integer pinsHitCountFirstTake) {
        this.pinsHitCountFirstTake = pinsHitCountFirstTake;
    }

    public Integer getPinsHitCountSecondTake() {
        return pinsHitCountSecondTake;
    }

    public void setPinsHitCountSecondTake(Integer pinsHitCountSecondTake) {
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

    public Integer getScore() {
        return Integer.sum(pinsHitCountFirstTake, pinsHitCountSecondTake);
    }

    public boolean isStrike() {
        return (pinsHitCountFirstTake != null && pinsHitCountFirstTake.intValue() == PINS_COUNT)
                || (pinsHitCountSecondTake != null && pinsHitCountSecondTake.intValue() == PINS_COUNT);
    }

    public boolean isSpare() {
        return false;
    }
    public boolean isMiss() {
        return pinsHitCountFirstTake == null && pinsHitCountSecondTake == null;
    }

}
