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

			 requestWriter.println("GET " + "http://www.google.be/?gfe_rd=cr&dcr=0&ei=5_isWpvDDo2r4gTYmoIQ" + " HTTP/1.1");
			 requestWriter.println("Host: " + "www.google.com");
			 requestWriter.println("User-Agent: Simple Http Client");
			 requestWriter.println("Accept: text/html");
			 requestWriter.println("Accept-Language: en-US");
			 requestWriter.println("Connection: close");
			 requestWriter.println();

			 InputStream input = socket.getInputStream();

			 BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			 String line;

			 PrintWriter responseWriter = new PrintWriter("response.html", "UTF-8");

			 while ((line = reader.readLine()) != null) {
				 System.out.println(line);
				 responseWriter.println(line); //write to html file
			 }

			 responseWriter.close();

		 } catch (UnknownHostException ex) {

			 System.out.println("Server not found: " + ex.getMessage());

		 } catch (IOException ex) {

			 System.out.println("I/O error: " + ex.getMessage());
		 }
	 }
}
