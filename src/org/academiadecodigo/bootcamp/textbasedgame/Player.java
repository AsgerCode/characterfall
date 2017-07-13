package org.academiadecodigo.bootcamp.textbasedgame;

import org.academiadecodigo.bootcamp.textbasedgame.view.TerminalView;

import java.io.IOException;

/**
 * Created by codecadet on 11/07/2017.
 */
public class Player {

    private String name = "";
    private int col;
    private int row;
    private TerminalView terminalView;

    public Player(String name, int col) {
        this.name = name;
        this.col = col;
        this.row = 0;

        try {
            terminalView = new TerminalView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validatePosition(int col, int row) {
        if (terminalView.getCellChar(col, row) == ' '){
            return true;
        }

        return false;
    }

    public boolean moveDirection(String direction) {

        switch (direction) {

            case "UP":

                if (validatePosition(col, row + 1)) {
                    row += 1;
                    return true;
                }

                return false;

            case "DOWN":

                if (validatePosition(col, row + 1)) {
                    row -= 1;
                    return true;
                }

                return false;

            case "LEFT":

                if (validatePosition(col, row + 1)) {
                    col += 1;
                    return true;
                }

                return false;

            case "RIGHT":

                if (validatePosition(col, row + 1)) {
                    col -= 1;
                    return true;
                }

                return false;

            default:

                return false;

        }
    }
}
