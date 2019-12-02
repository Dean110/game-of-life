package dev.katatime.gameoflife;

public class PetriDish {
    public PetriDish(int rows, int columns) {

    }

    public Status checkStatus(int row, int column) {
        if(row==3){
            throw new OutsideOfPetriDIshException("The dish is not that big.");
        }
        return Status.DEAD;
    }


}
