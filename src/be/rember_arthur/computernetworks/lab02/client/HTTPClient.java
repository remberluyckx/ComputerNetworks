package be.rember_arthur.computernetworks.lab02.client;

import com.oracle.tools.packager.IOUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class HTTPClient {
	public static void main(String argv[]) throws Exception
	 {
		 /*if (argv.length < 1) return;

		 URL url;

		 try {
			 url = new URL(argv[0]);
		 } catch (MalformedURLException ex) {
			 ex.printStackTrace();
			 return;
		 }

		 String hostname = url.getHost();
		 int port = 80;*/

		 //command, uri
		 String[] testArg = {"GET", "www.google.be/?gfe_rd=cr&dcr=0&ei=9OOuWtqzFJKq8weHv5_wBw"};
		 String command = testArg[0];
		 String Uri = testArg[1];
		 String hostname = Uri.substring(0, Uri.lastIndexOf(".") + 3); //voor .com, .org moet dit +4 zijn

		 try (Socket socket = new Socket(hostname, 80)) {

		 	 //set up output to server
			 OutputStream output = socket.getOutputStream();
			 PrintWriter requestWriter = new PrintWriter(output, true);

			 //form request
			 requestWriter.println(command + " " + Uri + " HTTP/1.1");
			 requestWriter.println("Host: " + hostname);
			 requestWriter.println("User-Agent: Simple Http Client");
			 requestWriter.println("Accept: text/html");
			 requestWriter.println("Accept-Language: en-US");
			 //requestWriter.println("Connection: close");
			 requestWriter.println();

			 //get response
			 InputStream input = socket.getInputStream();
			 BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			 String line;
			 PrintWriter responseWriter = new PrintWriter("response.html", "UTF-8");

			 int imagesAmount = 0;

			 while ((line = reader.readLine()) != null) {
				 System.out.println(line);

				 //write to html file
				 responseWriter.println(line);

				 //filter all img tags, filter src attribute, get image urls
				 if (line.contains("<img")) {
				 String imgTags[] = line.split("<img");
				 for (int i=1; i < imgTags.length; i++) {
					 String srcAttributes[] = imgTags[i].split("src=\"");
					 String srcUrl = srcAttributes[1].substring(0, srcAttributes[1].indexOf("\""));
					 System.out.println(srcUrl);

					 //GET images
					 requestWriter.println(command + " " + hostname + srcUrl + " HTTP/1.1");
					 requestWriter.println("Host: " + hostname);
					 requestWriter.println("User-Agent: Simple Http Client");
					 requestWriter.println("Accept: image/gif");
					 requestWriter.println("Accept-Language: en-US");
					 requestWriter.println("Connection: close"); // comment out after this works
					 requestWriter.println();

					 InputStream imgStream = socket.getInputStream();

					 File file = new File("image" + imagesAmount+1 +".png");
					 OutputStream outStream = new FileOutputStream(file);

					 byte[] buffer = new byte[8 * 1024];
					 int bytesRead;
					 while ((bytesRead = imgStream.read(buffer)) != -1) {
							 outStream.write(buffer, 352, bytesRead);
					 }
					imagesAmount++;
				 }

				 }
			 }

			 responseWriter.close();

		 } catch (UnknownHostException ex) {

			 System.out.println("Server not found: " + ex.getMessage());

		 } catch (IOException ex) {

			 System.out.println("I/O error: " + ex.getMessage());
		 }
	 }
}
