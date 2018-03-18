package be.rember_arthur.computernetworks.lab02.server;

import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
	public static void main(String argv[]) throws Exception
	 {
	 ServerSocket welcomeSocket = new ServerSocket(6789);
	 while(true)
	 {
		 Socket connectionSocket = welcomeSocket.accept();
		 Thread client = new Thread( new ClientThread(connectionSocket));
		 client.start();
	 }
	 }
}
