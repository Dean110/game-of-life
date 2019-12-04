package dev.katatime.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static dev.katatime.gameoflife.Status.ALIVE;
import static dev.katatime.gameoflife.Status.DEAD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertAll;


public class PetriDishTest {

    private PetriDish underTest;

    @BeforeEach
    void setUp() {
        underTest = new PetriDish(3, 3);
    }

    @Test
    public void checkStatusReturnsTheStatusOfIndividualCellInThePetriDishAndThrowsExceptionsIfOutOfBounds() {
        underTest.makeCellLivable(0, 1);
        assertAll(
                () -> assertThat(underTest.checkStatus(0, 0)).isEqualTo(DEAD),
                () -> assertThat(underTest.checkStatus(0, 1)).isEqualTo(ALIVE),
                () -> assertPetriDishException(3, 0, "The dish is not that big."),
                () -> assertPetriDishException(0, 3, "The dish is not that big."),
                () -> assertPetriDishException(-1, 0, "This location is invalid."),
                () -> assertPetriDishException(0, -1, "This location is invalid."),
                () -> assertPetriDishException(-1, -1, "This location is invalid.")
        );

    }

    @Test
    public void checkNeighborStatusesOfACenterPiece() {
        int result = underTest.countAliveNeighbors(1, 1);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void checkNeighborStatusesOfACenterPieceWithOneAliveNeighbor() {
        underTest.makeCellLivable(0, 0);
        int result = underTest.countAliveNeighbors(1, 1);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void checkNeighborStatusesOfACenterPieceWithTwoAliveNeighbors() {
        underTest.makeCellLivable(0, 0);
        underTest.makeCellLivable(1, 0);
        int result = underTest.countAliveNeighbors(1, 1);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void checkNeighborStatusesOfACenterPieceWithThreeAliveNeighbors() {
        underTest.makeCellLivable(0, 0);
        underTest.makeCellLivable(1, 0);
        underTest.makeCellLivable(2, 0);
        int result = underTest.countAliveNeighbors(1, 1);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void checkNeighborCountsOfAllCells() {
        populateSampleCell();
        assertAll(
                () -> assertLiveCellCount(1, 0, 0, "Upper left cell count:"),
                () -> assertLiveCellCount(3, 0, 1, "Upper center cell count:"),
                () -> assertLiveCellCount(1, 0, 2, "Upper right cell count:"),
                () -> assertLiveCellCount(3, 1, 0, "Center left cell count:"),
                () -> assertLiveCellCount(4, 1, 1, "Center cell count:"),
                () -> assertLiveCellCount(3, 1, 2, "Center right cell count:"),
                () -> assertLiveCellCount(1, 2, 0, "Lower left cell count:"),
                () -> assertLiveCellCount(3, 2, 1, "Lower center cell count:"),
                () -> assertLiveCellCount(1, 2, 2, "Lower right cell count:")
        );
    }

    private void assertLiveCellCount(int expectedLiveCellCount, int row, int column, String description) {
        assertThat(underTest.countAliveNeighbors(row, column)).as(description).isEqualTo(expectedLiveCellCount);
    }

    private void populateSampleCell() {
        underTest.makeCellLivable(0, 0);
        underTest.makeCellLivable(0, 2);
        underTest.makeCellLivable(1, 1);
        underTest.makeCellLivable(2, 0);
        underTest.makeCellLivable(2, 2);
    }

    private void assertPetriDishException(int row, int column, String expectedMessage) {
        try {
            underTest.checkStatus(row, column);
            fail("Expected exception not thrown.");
        } catch (OutsideOfPetriDIshException e) {
            assertThat(e.getMessage()).isEqualTo(expectedMessage);
        }
    }
}
