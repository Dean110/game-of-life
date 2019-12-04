package dev.katatime.gameoflife;

public class GameOfLife {
    private PetriDish petriDish;


    public GameOfLife(PetriDish petriDish) {
        this.petriDish = petriDish;

    }

    public void tick() {
        PetriDish newPetriDish = new PetriDish(petriDish.gridLength(), petriDish.gridHeight());
        for (int i = 0; i < petriDish.gridLength(); i++) {
            for (int j = 0; j < petriDish.gridHeight(); j++) {
                if (cellIsAlive(i, j) && cellHasSuitableNeighborCount(i, j) || cellCanBeRepopulated(i, j)) {
                    newPetriDish.makeCellLivable(i, j);
                }
            }
        }
        petriDish = newPetriDish;
    }


    private boolean cellCanBeRepopulated(int row, int column) {
        return !cellIsAlive(row, column) && petriDish.countAliveNeighbors(row, column) == 3;
    }

    private boolean cellIsAlive(int row, int column) {
        return petriDish.checkStatus(row, column).equals(Status.ALIVE);
    }

    private boolean cellHasSuitableNeighborCount(int row, int column) {
        return petriDish.countAliveNeighbors(row, column) > 1 && petriDish.countAliveNeighbors(row, column) < 4;
    }

    public PetriDish getPetriDish() {
        return petriDish;
    }
}
