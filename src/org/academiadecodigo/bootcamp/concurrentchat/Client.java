package org.academiadecodigo.bootcamp.concurrentchat;

import java.io.*;
import java.net.Socket;

/**
 * Created by codecadet on 04/07/17.
 */
public class Client {

    private Socket socket;

    public void initializeSocket(String host, int port){
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void startClient(){
        BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));

        String host = null;
        int port = 0;
        String message = null;

        try {
            System.out.print("Host ? ");
            host = terminalIn.readLine();
            System.out.print("Port ? ");
            port = Integer.parseInt(terminalIn.readLine());

        } catch (IOException e) {
            System.err.println(e);
        }

        initializeSocket(host,port);
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e);
        }


        Thread thread = new Thread(new ClientRunnable());
        thread.start();
        while (true) {
            try {
                message = in.readLine();
                if(message == null){
                    return;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
            System.out.println(message);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startClient();
    }

    private class ClientRunnable implements Runnable {
        private PrintWriter out;
        private BufferedReader terminalIn;

        public ClientRunnable() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                terminalIn = new BufferedReader(new InputStreamReader(System.in));
            } catch (IOException e) {
                System.err.println(e);
            }
        }


        @Override
        public void run() {
            String response;
            while (true) {
                try {
                    response = terminalIn.readLine();
                    out.println(response);
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }

    }

}
