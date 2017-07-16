package org.academiadecodigo.bootcamp.characterfall;

/**
 * Created by codecadet on 11/07/2017.
 */
public class Player {

    private String name;
    private int col;
    private int row;
    private String character;

    public Player(String name, int col, String character) {
        this.name = name;
        this.col = col;
        this.row = 3;
        this.character = character;

    }

    public int getCol(){
        return col;
    }

    public int getRow(){
        return row;
    }

    public String getCharacter(){
        return character;
    }

    public boolean validatePosition(int col, int row) {
        //if (terminalView.getCellChar(col, row) == ' '){
        //    return true;
        //}

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
