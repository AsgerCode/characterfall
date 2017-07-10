package org.academiadecodigo.bootcamp.concurrentchat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by codecadet on 04/07/17.
 */
public class WebServerChat {

    final private Queue<ClientDispatcher> clientDispatchers;

    public WebServerChat(){
        clientDispatchers = new ConcurrentLinkedQueue<>();
    }


    public void startChat(){
        //read port to open from terminal
        BufferedReader terminalIn = new BufferedReader(new InputStreamReader(System.in));

        ServerSocket serverSocket = null;
        Socket clientSocket;
        ClientDispatcher clientDispatcher;
        int port;


        System.out.print("Port ? ");

        try {
            port = Integer.parseInt(terminalIn.readLine());
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                clientDispatcher = new ClientDispatcher(clientSocket);
                clientDispatchers.offer(clientDispatcher);
                System.out.println("A connection to a chat client was established!");
                Thread thread = new Thread(clientDispatcher);
                thread.start();
            }catch (IOException e){
                System.err.println(e);
            }
        }
    }


    public static void main(String[] args) {
        WebServerChat webChat = new WebServerChat();
        webChat.startChat();
    }

    public synchronized void sendAll(String message){
       for(ClientDispatcher clientDispatcher: clientDispatchers) {
           clientDispatcher.send(message);
       }
    }

    public void refreshClientDispatchers() throws IOException{
        System.out.println("-------------------BEFORE--------------------");
        //print the current elements on the list of connections #DEBUG
        for(ClientDispatcher clientDispatcher: clientDispatchers) {
            System.out.println(clientDispatcher);
        }


        for(ClientDispatcher clientDispatcher: clientDispatchers) {
            if(!clientDispatcher.isAlive()){
               if(clientDispatchers.remove(clientDispatcher)){
                   System.out.println("A connection to a chat client was removed.");
               }
            }
        }

        System.out.println("-------------------AFTER--------------------");
        //print the current elements on the list of connections #DEBUG
        for(ClientDispatcher clientDispatcher: clientDispatchers) {
            System.out.println(clientDispatcher);
        }

    }


    private class ClientDispatcher implements Runnable{
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String name;

        public ClientDispatcher(Socket clientSocket){
            this.clientSocket = clientSocket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(),true);
                askUserName();
                tellOthersAnotherUserEnteredTheChat();
            } catch (IOException e){
                System.err.println(e);
            }
        }

        public void askUserName() throws IOException{
            out.println("What is your user name?");
            name = in.readLine();
            out.println("Welcome to the chat "+name);
        }

        public void tellOthersAnotherUserEnteredTheChat(){
            sendAll(name+" has entered the chat.#########");
        }

        @Override
        public void run() {
            String message = null;
            while(true){
                try {
                    message = in.readLine();

                    if(message.equals("logout")){
                        sendAll(this.name+" has left the chat");
                        closeConnection();
                        refreshClientDispatchers();
                        break;
                    }

                } catch (IOException e) {
                    System.err.println(e);
                }
                sendAll("<"+ name +">: "+message);
              //  System.out.println("<"+ name +">: "+message);
            }
        }

        public void send(String message){
            out.println(message);
        }

        public void closeConnection() throws IOException{
            clientSocket.close();
        }

        public boolean isAlive(){
            return !clientSocket.isClosed();
        }

        @Override
        public String toString() {
            return "ClientDispatcher{" +
                    "IP = " + clientSocket.getInetAddress().getHostName() +
                    ", name = '" + name + '\'' +
                    '}';
        }
    }
}
