package org.academiadecodigo.bootcamp.textbasedgame.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TerminalView {

    private Cell[][] fieldGrid;
    private final int COLSIZE = 20;
    private final int ROWSIZE = 10;
    private final String PATH = "/Users/codecadet/workgroup/textbasedgame/resources/gametext.txt";

    public TerminalView() throws IOException {

        this.fieldGrid = new Cell[COLSIZE][ROWSIZE];
        createCells(PATH);
        generateField();

    }

    public void createCells(String path) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));

        for (int row = 0; row < ROWSIZE; row++) {
            for (int col = 0; col < COLSIZE; col++) {

                fieldGrid[col][row] = new Cell(col, row, (char) bf.read());
            }
        }
    }


    public void generateField() {

        for (int row = 0; row < ROWSIZE; row++) {
            for (int col = 0; col < COLSIZE; col++) {

                if (fieldGrid[col][row].getCharacter() == '\n'){
                    fieldGrid[col][row].setCharacter(' ');
                }
                System.out.print(fieldGrid[col][row].getCharacter());
                //System.out.print("[" + col + "]" + "[" + row + "],");
            }
            System.out.println();
        }
    }

    public void characterSelect() {
        return;
    }

    public void readLevel() {
        return;
    }

    public void removeCharacters(char character) {
        for (int col = 0; col < COLSIZE; col++) {
            for (int row = 0; row < ROWSIZE; row++) {
                if (fieldGrid[col][row].getCharacter() == character) {
                    fieldGrid[col][row].setCharacter(' ');
                }

            }
        }
        generateField();

    }

    public char getCellChar(int col, int row) {
        return fieldGrid[col][row].getCharacter();
    }

    public void removeTextNewLine(){
        return;
    }
}
