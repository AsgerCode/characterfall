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
    private Client client;
    private Server server;

    public void start() {
        try {
            server = new Server();
            client = new Client();
            terminalview = new TerminalView();

            terminalview.generateField();
            server.startChat();
            client.startClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
