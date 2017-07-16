package org.academiadecodigo.bootcamp.characterfall.view;

public class Cell {

    private int col;
    private int row;
    private String character;

    public Cell(int col, int row, String character) {
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

    public String getCharacter() {
        return character;
    }


    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}

