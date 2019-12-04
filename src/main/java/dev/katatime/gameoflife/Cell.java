package dev.katatime.gameoflife;

import static dev.katatime.gameoflife.Status.ALIVE;
import static dev.katatime.gameoflife.Status.DEAD;

public class Cell {
    private Status status = DEAD;

    public void bringToLife() {
        status = ALIVE;
    }

    public Status status() {
        return status;
    }
}
