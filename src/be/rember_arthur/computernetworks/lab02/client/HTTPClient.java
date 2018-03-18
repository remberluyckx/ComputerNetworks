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

		 try (Socket socket = new Socket("www.google.com", 80)) {

			 OutputStream output = socket.getOutputStream();
			 PrintWriter requestWriter = new PrintWriter(output, true);

			 requestWriter.println("GET " + "http://www.google.be/?gfe_rd=cr&dcr=0&ei=k2uuWrPcHcOh4gTf44_ICQ" + " HTTP/1.1");
			 requestWriter.println("Host: " + "www.google.com");
			 requestWriter.println("User-Agent: Simple Http Client");
			 requestWriter.println("Accept: text/html");
			 requestWriter.println("Accept-Language: en-US");
			 //requestWriter.println("Connection: close");
			 requestWriter.println();

			 InputStream input = socket.getInputStream();

			 BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			 String line;

			 PrintWriter responseWriter = new PrintWriter("response.html", "UTF-8");

			 while ((line = reader.readLine()) != null) {
				 System.out.println(line);
				 responseWriter.println(line); //write to html file

				 //filter all img tags, filter src attribute, get image urls
				 if (line.contains("<img")) {
				 String imgTags[] = line.split("<img");
				 for (int i=1; i < imgTags.length; i++) {
					 String srcAttributes[] = imgTags[i].split("src=\"");
					 String srcUrl = srcAttributes[1].substring(0, srcAttributes[1].indexOf("\""));
					 System.out.println(srcUrl);

					 //OutputStream output2 = socket.getOutputStream();
					 //PrintWriter requestWriter2 = new PrintWriter(output2, true);

					 requestWriter.println("GET " + "http://www.google.com" + srcUrl + " HTTP/1.1");
					 requestWriter.println("Host: " + "www.google.com");
					 requestWriter.println("User-Agent: Simple Http Client");
					 requestWriter.println("Accept: image/gif");
					 requestWriter.println("Accept-Language: en-US");
					 //requestWriter.println("Connection: close"); // comment out after this works
					 requestWriter.println();

					 InputStream imgStream = socket.getInputStream();

					 /*BufferedImage imBuff = ImageIO.read(imgStream);

					 Image img = ImageIO.read(imgStream);



					 File outputfile = new File("image.png");
					 ImageIO.write(imBuff, "png", outputfile);*/



					 File file = new File("image.png");

					 OutputStream outStream = new FileOutputStream(file);

					 byte[] buffer = new byte[8 * 1024];
					 int bytesRead;
					 while ((bytesRead = imgStream.read(buffer)) != -1) {
							 outStream.write(buffer, 333, bytesRead);
					 }


					 /*BufferedReader reader2 = new BufferedReader(new InputStreamReader(imgStream));

					 String line2;

					 while ((line2 = reader2.readLine()) != null) {
						 System.out.println("NL" + line2);
					 }*/
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
