package dev.katatime.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PetriDish {
    private List<List<Cell>> cells;

    public PetriDish(int rows, int columns) {
       populateCells(rows, columns);

    }

    private void populateCells(int rows, int columns) {
        cells = new ArrayList<>();
        for (int i = 0; i<rows; i++){
            List<Cell> rowToAdd= new ArrayList<>();
            for (int j = 0; j < rows; j++){
                rowToAdd.add(new Cell());
            }
            cells.add(rowToAdd);
        }
    }

    public Status checkStatus(int row, int column) {
        if(row>=cells.size()||column>=cells.get(0).size()){
            throw new OutsideOfPetriDIshException("The dish is not that big.");
        }
        return Status.DEAD;
    }


}
