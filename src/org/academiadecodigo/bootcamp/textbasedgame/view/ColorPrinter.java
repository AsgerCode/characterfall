package org.academiadecodigo.bootcamp.textbasedgame.view;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import static com.diogonunes.jcdp.color.api.Ansi.Attribute.*;

/**
 * Created by ritafialho on 16/07/2017.
 */
public class ColorPrinter {

    private ColoredPrinter coloredPrinter;
    private final Ansi.BColor NEW_WHITE = Ansi.BColor.BLACK;


    public ColorPrinter(){
        coloredPrinter = new ColoredPrinter.Builder(0,false).build();
    }
    private void printAlternatedCharacters(Ansi.BColor startColor, Ansi.BColor nextColor) {
        for (int i = 0; i < 150 ; i++) {
            coloredPrinter.setBackgroundColor(startColor);
            coloredPrinter.print(" ");
            coloredPrinter.setBackgroundColor(nextColor);
            coloredPrinter.print(" ");
        }
        coloredPrinter.println("");
        coloredPrinter.clear();
    }
    public void printCheckedField(Ansi.BColor startColor, Ansi.BColor nextColor){
        for (int i = 0; i < 20 ; i++) {
            printAlternatedCharacters(startColor,nextColor);
            printAlternatedCharacters(nextColor,startColor);
        }
    }
    public void printCharactersWithDifferentForegroundColor(Ansi.FColor color, String line, String characters){

        char c;
        for (int i = 0; i < line.length() ; i++) {
            c = line.charAt(i);
            if(hasCharacter(c,characters)){

                coloredPrinter.setForegroundColor(color);
                coloredPrinter.print(c);
            }
            else{

                coloredPrinter.setForegroundColor(Ansi.FColor.BLACK);
                coloredPrinter.print(c);
            }
            coloredPrinter.clear();
        }
    }

    public void printCharactersWithDifferentBackgroundColor(Ansi.BColor color, String line , String characters){
        char c;
        for (int i = 0; i < line.length() ; i++) {
            c = line.charAt(i);
            if(hasCharacter(c,characters)){

                coloredPrinter.setBackgroundColor(color);
                coloredPrinter.print(c);
            }
            else{
                coloredPrinter.setForegroundColor(Ansi.FColor.BLACK);
                coloredPrinter.print(c);
            }
            coloredPrinter.clear();
        }
    }

    private boolean hasCharacter(char target, String line ){
        char c;
        for (int i = 0; i <  line.length(); i++) {
            c = line.charAt(i);
            if(c == target){
                return true;
            }
        }
        return false;
    }
    public void printAllForeGroundColors(String message){
        for(Ansi.FColor c: Ansi.FColor.values()){
            coloredPrinter.setForegroundColor(c);
            coloredPrinter.println(message);
        }
        coloredPrinter.clear();
    }
    public void printAllBackGroundColor(String message){
        for (Ansi.BColor c :Ansi.BColor.values()) {
            coloredPrinter.setBackgroundColor(c);
            coloredPrinter.println(message);
        }
        coloredPrinter.clear();
    }

    public ColoredPrinter getColoredPrinter(){
        return coloredPrinter;
    }





    public static void main(String[] args) {
        ColorPrinter vt = new ColorPrinter();
        String line = "\n" +
                "      ___           ___           ___           ___           ___           ___           ___           ___           ___                    ___           ___           ___       ___ \n" +
                "     /\\  \\         /\\__\\         /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\  \\         /\\  \\                  /\\  \\         /\\  \\         /\\__\\     /\\__\\\n" +
                "    /::\\  \\       /:/  /        /::\\  \\       /::\\  \\       /::\\  \\       /::\\  \\        \\:\\  \\       /::\\  \\       /::\\  \\                /::\\  \\       /::\\  \\       /:/  /    /:/  /\n" +
                "   /:/\\:\\  \\     /:/__/        /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\        \\:\\  \\     /:/\\:\\  \\     /:/\\:\\  \\              /:/\\:\\  \\     /:/\\:\\  \\     /:/  /    /:/  / \n" +
                "  /:/  \\:\\  \\   /::\\  \\ ___   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\   /:/  \\:\\  \\       /::\\  \\   /::\\~\\:\\  \\   /::\\~\\:\\  \\            /::\\~\\:\\  \\   /::\\~\\:\\  \\   /:/  /    /:/  /  \n" +
                " /:/__/ \\:\\__\\ /:/\\:\\  /\\__\\ /:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/__/ \\:\\__\\     /:/\\:\\__\\ /:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\          /:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/__/    /:/__/   \n" +
                " \\:\\  \\  \\/__/ \\/__\\:\\/:/  / \\/__\\:\\/:/  / \\/_|::\\/:/  / \\/__\\:\\/:/  / \\:\\  \\  \\/__/    /:/  \\/__/ \\:\\~\\:\\ \\/__/ \\/_|::\\/:/  /          \\/__\\:\\ \\/__/ \\/__\\:\\/:/  / \\:\\  \\    \\:\\  \\   \n" +
                "  \\:\\  \\            \\::/  /       \\::/  /     |:|::/  /       \\::/  /   \\:\\  \\         /:/  /       \\:\\ \\:\\__\\      |:|::/  /                \\:\\__\\        \\::/  /   \\:\\  \\    \\:\\  \\  \n" +
                "   \\:\\  \\           /:/  /        /:/  /      |:|\\/__/        /:/  /     \\:\\  \\        \\/__/         \\:\\ \\/__/      |:|\\/__/                  \\/__/        /:/  /     \\:\\  \\    \\:\\  \\ \n" +
                "    \\:\\__\\         /:/  /        /:/  /       |:|  |         /:/  /       \\:\\__\\                      \\:\\__\\        |:|  |                                /:/  /       \\:\\__\\    \\:\\__\\\n" +
                "     \\/__/         \\/__/         \\/__/         \\|__|         \\/__/         \\/__/                       \\/__/         \\|__|                                \\/__/         \\/__/     \\/__/\n";


        ColoredPrinter cp = vt.getColoredPrinter();
        //print all colors from enum order
        /*
        BLACK ->  NEW WHITE
        RED
        GREEN
        YELLOW
        BLUE
        MAGENTA
        CYAN
        WHITE -> current terminal color
        NONE  -> current terminal color
         */
       /* vt.printAllForeGroundColors(line);
        //print all background colors
        vt.printAllBackGroundColor("");
        //print checked field
        vt.printCheckedField(vt.NEW_WHITE,Ansi.BColor.RED);
        vt.printCheckedField(vt.NEW_WHITE,Ansi.BColor.BLUE);
        vt.printCheckedField(vt.NEW_WHITE,Ansi.BColor.YELLOW);
        vt.coloredPrinter.setBackgroundColor(Ansi.BColor.NONE);
        vt.coloredPrinter.setAttribute(Ansi.Attribute.REVERSE);
        vt.printAllForeGroundColors(line);
        vt.coloredPrinter.clear();
        */
        vt.printCharactersWithDifferentForegroundColor(Ansi.FColor.CYAN,line,"/_\\|");


        //resetting the terminal to its default colors
        cp.clear();
        //   cp.print(cp.getDateFormatted(),DARK , Ansi.FColor.CYAN, Ansi.BColor.BLACK);
        // cp.debugPrintln(" This debug message is always printed.");
        cp.clear();
        cp.print("This example used JCDP 1.25   ");
        //temporarily overriding that format
        cp.print("\tMADE ", BOLD, Ansi.FColor.RED, Ansi.BColor.GREEN);
        cp.print("IN PORTUGAL\t\n", BOLD, Ansi.FColor.YELLOW, Ansi.BColor.RED);
        cp.println("I hope you find it useful ;)");
        cp.clear(); //don't forget to clear the terminal's format before exiting
    }
}

