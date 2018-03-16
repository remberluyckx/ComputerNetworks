package be.rember_arthur.computernetworks.lab02.server;

import java.io.*;
import java.net.*;

public class HTTPServer {
	public static void main(String argv[]) throws Exception
	 {
	 ServerSocket welcomeSocket = new ServerSocket(6789);
	 while(true)
	 {
		 Socket connectionSocket = welcomeSocket.accept();
		 ClientThread client = new ClientThread(connectionSocket);
		 client.run();
	 }
	 }
}
