package be.rember_arthur.computernetworks.lab02.client;

import java.io.*;
import java.net.*;

public class HTTPClient {
	public static void main(String argv[]) throws Exception
	 { 
		System.out.println("1");
	 BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	 	System.out.println("2");
	 Socket clientSocket = new Socket("google.com", 80);
	 System.out.println("3");
	 DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
	 BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	 String sentence = inFromUser.readLine();
	 outToServer.writeBytes(sentence + '\n');
	 String modifiedSentence = inFromServer.readLine();
	 System.out.println("FROM SERVER: " + modifiedSentence);
	 clientSocket.close();
	 }
}
