package dev.katatime.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static dev.katatime.gameoflife.Status.ALIVE;
import static dev.katatime.gameoflife.Status.DEAD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameOfLifeTest {
    private GameOfLife underTest;
    private PetriDish petriDish;

    @BeforeEach
    public void setup() {
        petriDish = new PetriDish(3, 3);
        underTest = new GameOfLife(petriDish);
    }

    @Test
    public void livingCellWithNoLiveNeighborsDiesAfterTick() {
        petriDish.makeCellLivable(1, 1);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(DEAD);
    }

    @Test
    public void livingCellWithOneLivingNeighborDiesAfterTick() {
        petriDish.makeCellLivable(1, 1);
        petriDish.makeCellLivable(0, 0);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(DEAD);
    }

    @Test
    public void livingCellWithTwoLivingNeighborLivesAfterTick() {
        petriDish.makeCellLivable(1, 1);
        petriDish.makeCellLivable(0, 0);
        petriDish.makeCellLivable(0, 1);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(ALIVE);
    }

    @Test
    public void deadCellWithTwoLivingNeighborStaysDeadAfterTick() {
        petriDish.makeCellLivable(0, 0);
        petriDish.makeCellLivable(0, 1);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(DEAD);
    }

    @Test
    public void livingCellWithThreeLivingNeighborLivesAfterTick() {
        petriDish.makeCellLivable(1, 1);
        petriDish.makeCellLivable(0, 0);
        petriDish.makeCellLivable(0, 1);
        petriDish.makeCellLivable(0, 2);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(ALIVE);
    }

    @Test
    public void deadCellWithThreeLivingNeighborLivesAfterTick() {
        petriDish.makeCellLivable(0, 0);
        petriDish.makeCellLivable(0, 1);
        petriDish.makeCellLivable(0, 2);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(ALIVE);
    }

    @Test
    public void livingCellWithFourLivingNeighborDiesAfterTick() {
        petriDish.makeCellLivable(1, 1);
        petriDish.makeCellLivable(0, 0);
        petriDish.makeCellLivable(0, 1);
        petriDish.makeCellLivable(0, 2);
        petriDish.makeCellLivable(1, 0);
        underTest.tick();
        assertThat(underTest.getPetriDish().checkStatus(1, 1)).isEqualTo(DEAD);
    }

    @Test
    public void tickShouldAffectAllCells() {
        populatePetriDish();
        underTest.tick();
        assertAll(
                () -> assertCellStatus(DEAD, 0, 0, "Upper left cell:"),
                () -> assertCellStatus(ALIVE, 0, 1, "Upper center cell:"),
                () -> assertCellStatus(DEAD, 0, 2, "Upper right cell:"),
                () -> assertCellStatus(ALIVE, 1, 0, "Center left cell:"),
                () -> assertCellStatus(DEAD, 1, 1, "Center cell:"),
                () -> assertCellStatus(ALIVE, 1, 2, "Center right cell:"),
                () -> assertCellStatus(DEAD, 2, 0, "Lower left cell:"),
                () -> assertCellStatus(ALIVE, 2, 1, "Lower center cell:"),
                () -> assertCellStatus(DEAD, 2, 2, "Lower right cell:")

        );

    }

    private void assertCellStatus(Status status, int row, int column, String description) {
        assertThat(underTest.getPetriDish().checkStatus(row, column)).as(description).isEqualTo(status);
    }

    private void populatePetriDish() {
        petriDish.makeCellLivable(0, 0);
        petriDish.makeCellLivable(0, 2);
        petriDish.makeCellLivable(1, 1);
        petriDish.makeCellLivable(2, 0);
        petriDish.makeCellLivable(2, 2);
    }

}
