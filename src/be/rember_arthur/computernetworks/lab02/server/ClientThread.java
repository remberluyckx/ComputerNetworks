package be.rember_arthur.computernetworks.lab02.server;

import java.io.*;
import java.net.*;

/**
 * ClientThread is the threadable container around an TCP session with a client.
 */
public class ClientThread implements Runnable  {

    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
            socket.getInputStream().read();
            String clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            String capsSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capsSentence);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
