package dev.katatime.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class PetriDish {
    private List<List<Cell>> cells;

    public PetriDish(int rows, int columns) {
        populateCells(rows, columns);
    }

    private void populateCells(int rows, int columns) {
        cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Cell> rowToAdd = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                rowToAdd.add(new Cell());
            }
            cells.add(rowToAdd);
        }
    }

    public Status checkStatus(int row, int column) {
        if (isOutsideOfPetriDish(row, column)) {
            throw new OutsideOfPetriDIshException("The dish is not that big.");
        }
        if (isAnInvalidLocation(row, column)) {
            throw new OutsideOfPetriDIshException("This location is invalid.");
        }
        return cells.get(row).get(column).status();
    }

    private boolean isAnInvalidLocation(int row, int column) {
        return row < 0 || column < 0;
    }

    private boolean isOutsideOfPetriDish(int row, int column) {
        return row >= cells.size() || column >= cells.get(0).size();
    }


    public void makeCellLivable(int row, int column) {
        cells.get(row).get(column).bringToLife();
    }

    public int countAliveNeighbors(int row, int column) {
        int liveCellCount = 0;

        liveCellCount += countUpperNeighbors(row, column);
        liveCellCount += countRowNeighbors(row, column);
        liveCellCount += countLowerRowNneighbors(row, column);

        return liveCellCount;
    }

    private int countUpperNeighbors(int row, int column) {
        int liveCellCount = 0;
        if (row > 0) {
            for (int i = column - 1; i <= column + 1; i++) {
                if (i >= 0 && i < cells.get(row).size()) {
                    if (checkStatus(row - 1, i).equals(Status.ALIVE)) {
                        liveCellCount++;
                    }
                }
            }
        }
        return liveCellCount;
    }

    private int countRowNeighbors(int row, int column) {
        int liveCellCount = 0;
        for (int i = column - 1; i <= column + 1; i += 2) {
            if (i >= 0 && i < cells.get(row).size()) {
                if (checkStatus(row, i).equals(Status.ALIVE)) {
                    liveCellCount++;
                }
            }
        }
        return liveCellCount;
    }

    private int countLowerRowNneighbors(int row, int column) {
        int liveCellCount = 0;
        if (row + 1 < cells.size()) {
            for (int i = column - 1; i <= column + 1; i++) {
                if (i >= 0 && i < cells.get(row).size()) {
                    if (checkStatus(row + 1, i).equals(Status.ALIVE)) {
                        liveCellCount++;
                    }
                }
            }
        }
        return liveCellCount;
    }

    public int gridLength() {
        return cells.size();
    }

    public int gridHeight() {
        return cells.get(0).size();
    }
}
