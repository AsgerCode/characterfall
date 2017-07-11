package org.academiadecodigo.bootcamp.textbasedgame.tests;
import org.academiadecodigo.bootcamp.textbasedgame.view.TerminalView;

import java.io.IOException;

public class Main {


    public static void main(String[] args)  {

        try {
            TerminalView terminalView = new TerminalView(50, 30);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
