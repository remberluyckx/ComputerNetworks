package be.rember_arthur.computernetworks.lab02.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * ClientThread is the threadable container around an HTTP session with a client.
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
