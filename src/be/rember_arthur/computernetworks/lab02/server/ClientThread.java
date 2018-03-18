package be.rember_arthur.computernetworks.lab02.server;

import java.io.*;
import java.net.*;

/**
 * ClientThread is the threadable container around an TCP session with a client.
 */
class ClientThread implements Runnable  {

    private Socket socket;

    ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("New Client connected");
        try {
            HTTPRequest request = new HTTPRequest(socket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            /*String clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            String capsSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capsSentence);*/
            socket.close();
        } catch (Exception e) {
        }
        System.out.println("Client disconnected");
    }
}
