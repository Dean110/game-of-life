package dev.katatime.gameoflife;

import org.junit.jupiter.api.Test;

import static dev.katatime.gameoflife.Status.DEAD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertAll;


public class PetriDishTest {
    @Test
    public void threeByThreeShouldHaveThreeRowsOfThreeColumnsOfCells(){
        PetriDish underTest = new PetriDish(3,3);
        assertAll(
                ()->assertThat(underTest.checkStatus(0,0)).isEqualTo(DEAD),
                ()->{
                    try{
                        underTest.checkStatus(3,3);
                        fail("Expected exception not thrown.");
                    }catch (OutsideOfPetriDIshException e){
                        assertThat(e.getMessage()).isEqualTo("The dish is not that big.");
                    }
                }
                );

    }
}
