package org.academiadecodigo.bootcamp.textbasedgame;

/**
 * Created by codecadet on 11/07/2017.
 */
public class Player {

    private String name = "";
    private int col;
    private int row;

    public Player(String name, int col, int row) {
        this.name = name;
        this.col = col;
        this.row = 0;
    }

    public boolean validatePosition() {
        if (row - 1 == "") {
            return false;
        }

        return true;
    }

    public void move() {
        if (validatePosition()) {
            row -= 1;
            return;
        }

        return;
    }
}
