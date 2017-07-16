package org.academiadecodigo.bootcamp.textbasedgame;

import org.academiadecodigo.bootcamp.textbasedgame.serverclient.Client;
import org.academiadecodigo.bootcamp.textbasedgame.serverclient.Server;
import org.academiadecodigo.bootcamp.textbasedgame.view.TerminalView;

import java.io.IOException;

/**
 * Created by codecadet on 11/07/2017.
 */
public class Game {

    private TerminalView terminalview;
    private Player playerOne;
    private Player playerTwo;
    private Client client;
    private Server server;

    public void start() {
        boolean endGame = false;

        try {
          //  server = new Server();
          //  client = new Client();
            terminalview = new TerminalView();
            playerOne = new Player("Piriquito",2,"\u00a9");
            playerTwo = new Player("Gafanhoto",92,"\u00A5");

            terminalview.populatePlayer(playerOne.getCol(), playerOne.getRow(), playerOne.getCharacter());
            terminalview.populatePlayer(playerTwo.getCol(),playerTwo.getRow(),playerTwo.getCharacter());
            terminalview.printGrid();
            startPLayerTurn(playerOne);

<<<<<<< HEAD
            terminalview.generateField();
            server.startChat();
            client.startClient();

=======

            //terminalview.generateField();
           // server.startChat();
            //client.startClient();
>>>>>>> origin/gameplay
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startPLayerTurn(Player player){
        String playerInput = terminalview.terminalInputReader();
        if(playerInput != null && playerInput.length() > 1){
            setPlayerMessage("Wrong user input. Insert again.");

            //System.out.println("\u001b[2J");
            terminalview.printGrid();
            startPLayerTurn(player);
        }

        updateGridWithPlayerChoice(playerInput);

    }

    public void setPlayerMessage(String message){
        terminalview.setRowMessage(message);
    }

    public void updateGridWithPlayerChoice(String playerInput){
        terminalview.printLogo();
        terminalview.removeCharacters(playerInput);
        terminalview.updateGridWithPlayerChoice();
        terminalview.printGrid();
    }




}
