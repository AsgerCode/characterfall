package org.academiadecodigo.bootcamp.textbasedgame.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TerminalView {

    private Cell[][] fieldGrid;
    private final int COLSIZE = 50;
    private final int ROWSIZE = 30;
    private final String PATH = "/Users/codecadet/workgroup/textbasedgame/resources/gametext.txt";

    public TerminalView(int cols, int rows) throws IOException {

        this.fieldGrid = new Cell[cols][rows];
        createCells(PATH);
        generateField();

    }

    public void createCells(String path) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(new File(path)));

        for (int col = 0; col < COLSIZE; col++) {
            for (int row = 0; row < ROWSIZE; row++) {
                fieldGrid[col][row] = new Cell(col, row, (char) bf.read());
            }
        }
    }


    public void generateField() {
        for (int col = 0; col < COLSIZE; col++) {
            for (int row = 0; row < ROWSIZE; row++) {
                System.out.print(fieldGrid[col][row].getCharacter());
            }
        }
    }

    public void characterSelect() {
        return;
    }

    public void readLevel() {
        return;
    }

    public void removeCharacters() {
        return;
    }
}
