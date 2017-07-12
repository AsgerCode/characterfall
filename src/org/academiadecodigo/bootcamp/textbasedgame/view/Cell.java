package org.academiadecodigo.bootcamp.textbasedgame.view;

public class Cell {

    private int col;
    private int row;
    private char character;

    public Cell(int col, int row, char character) {
        this.col = col;
        this.row = row;
        this.character = character;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public char getCharacter() {
        return character;
    }


    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}

