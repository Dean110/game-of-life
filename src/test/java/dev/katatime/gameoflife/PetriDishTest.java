package dev.katatime.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void threeByThreeShouldHaveThreeRowsOfThreeColumnsOfCells() {
        assertAll(
                () -> assertThat(underTest.checkStatus(0, 0)).isEqualTo(DEAD),
                () -> assertPetriDishException(3, 0, "The dish is not that big."),
                () -> assertPetriDishException(0, 3, "The dish is not that big.")
        );

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
