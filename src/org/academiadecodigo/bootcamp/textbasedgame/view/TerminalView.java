package org.academiadecodigo.bootcamp.textbasedgame.view;

import com.diogonunes.jcdp.color.api.Ansi;

import java.io.*;
import java.util.Scanner;

public class TerminalView {

    private Cell[][] fieldGrid;
    private final int COLSIZE = 184;
    private final int ROWSIZE = 20;
    private final String PATH = "resources/gametext.txt";
    private final String LOGO_PATH = "resources/logo.txt";
    private final String CHARACTERS_TO_COLOR = "/_\\|";
    private ColorPrinter colorPrinter;
    Scanner scanner = new Scanner(System.in);
    private final int INITIAL_PLAYER_ROW = 3;
    private final String symbolPlayerOne = "\u00A9";
    private final String symbolPlayerTwo = "\u00A5";


    public TerminalView() throws IOException {

        colorPrinter = new ColorPrinter();
        this.fieldGrid = new Cell[COLSIZE][ROWSIZE];
        printLogo();
        createCells();
        setRowMessage("Enter your nickname: ");
        generateTextField();
    }

    public void printLogo() {

        String logo = FileManager.load(LOGO_PATH);
        colorPrinter.printCharactersWithDifferentForegroundColor(Ansi.FColor.CYAN, logo, CHARACTERS_TO_COLOR);
    }

    public void populatePlayer(int col, int row, String character) {
        if (col < (COLSIZE / 2)) {
            col = (int) (Math.random() * ((COLSIZE - 4) / 2)) + 2;
        } else {                                                       //(math.random()* MAX - MIN ) + MIN
            col = (int) (Math.random() * ((COLSIZE - 4  - (COLSIZE - 4) / 2)) + (COLSIZE - 4) / 2);
        }

        fieldGrid[col][row].setCharacter(character);
    }

    public void createCells() throws IOException {

        for (int row = 0; row < ROWSIZE; row++) {
            for (int col = 0; col < COLSIZE; col++) {

                fieldGrid[col][row] = new Cell(col, row, " ");
            }
        }
        generateFieldOutline();
    }

    public void printGrid() {

        for (int row = 0; row < ROWSIZE; row++) {
            for (int col = 0; col < COLSIZE; col++) {


                    if (fieldGrid[col][row].getCharacter().matches("\u00A9")) {
                        colorPrinter.printCharactersWithDifferentBackgroundColor(Ansi.BColor.RED, " ", " ");
                    } else if (fieldGrid[col][row].getCharacter().matches("\u00A5")) {
                        colorPrinter.printCharactersWithDifferentBackgroundColor(Ansi.BColor.BLUE, " ", " ");
                    }

                 else {
                    System.out.print(fieldGrid[col][row].getCharacter());
                }
            }
            System.out.println();
        }
    }

    private void generateFieldOutline() {

        for (int row = 0; row < ROWSIZE; row++) {
            for (int col = 0; col < COLSIZE; col++) {

                fieldGrid[0][0].setCharacter("+");
                fieldGrid[col][0].setCharacter("-");
                fieldGrid[COLSIZE - 1][0].setCharacter("+");
                fieldGrid[0][1].setCharacter("|");
                fieldGrid[0][row].setCharacter("|");
                fieldGrid[col][1].setCharacter(" ");
                fieldGrid[0][2].setCharacter("+");
                fieldGrid[col][2].setCharacter("-");
                fieldGrid[COLSIZE - 1][2].setCharacter("+");
                fieldGrid[COLSIZE - 1][1].setCharacter("|");
                fieldGrid[COLSIZE - 1][row].setCharacter("|");
                fieldGrid[0][ROWSIZE - 1].setCharacter("+");
                fieldGrid[col][ROWSIZE - 1].setCharacter("-");
                fieldGrid[COLSIZE - 1][ROWSIZE - 1].setCharacter("+");
                fieldGrid[0][ROWSIZE - 3].setCharacter("+");
                fieldGrid[col][ROWSIZE - 3].setCharacter("-");
                fieldGrid[COLSIZE - 1][ROWSIZE - 3].setCharacter("+");
            }
        }
        fieldGrid[COLSIZE / 2][2].setCharacter("|");
    }


    public void generateTextField() {

        BufferedReader bf = null;
        try {
            bf = new BufferedReader(new FileReader(new File(PATH)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int character;
        String symbol = "";
        for (int row = 4; row < ROWSIZE - 1; row++) {
            for (int col = 2; col < COLSIZE - 2; col++) {

                try {

                    if ((character = bf.read()) != -1) {
                        symbol = Character.toString((char) character);
                        if (symbol.equals(" ")) {
                            fieldGrid[col][row].setCharacter("_");
                        } else {
                            fieldGrid[col][row].setCharacter(symbol);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fieldGrid[col][row].getCharacter().equals("\n")) {
                    fieldGrid[col][row].setCharacter("_");
                }
            }
        }
    }

    public String terminalInputReader() {

        return scanner.nextLine();
    }

    public void setRowMessage(String message) {

        for (int col = 0; col < message.length(); col++) {
            fieldGrid[col + 2][ROWSIZE - 2].setCharacter(message.substring(col, col + 1));
        }
    }

    public void removeCharacters(String character) {
        for (int row = 4; row < ROWSIZE - 1; row++) {
            for (int col = 2; col < COLSIZE - 2; col++) {
                if (fieldGrid[col][row].getCharacter().equals(character)) {
                    fieldGrid[col][row].setCharacter("*");
                }
            }
        }
    }

    public void updateGridWithPlayerChoice() {
        for (int row = ROWSIZE - 3; row > 3; --row) {
            for (int col = COLSIZE - 2; col > 1; --col) {
                if(!fieldGrid[col][row].getCharacter().equals(" ")){
                    if (fieldGrid[col][row].getCharacter().equals("*")) {
                        pullCharactersDown(col, row);
                    }
                }
            }
        }
    }

    private void pullCharactersDown(int col, int row) {
        String character;
        for (int i = row; i > 3; --i) {
            character = fieldGrid[col][i - 1].getCharacter();
            if(character.matches(symbolPlayerOne)){
                fieldGrid[col][i - 1].setCharacter(" ");
            }
            if(character.matches(symbolPlayerTwo)){
                fieldGrid[col][i - 1].setCharacter(" ");
            }

            fieldGrid[col][i].setCharacter(character);
        }
    }
}
