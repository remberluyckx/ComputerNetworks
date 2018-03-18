package be.rember_arthur.computernetworks.lab02.server;

import java.io.*;
import java.util.*;

/**
 * This class embodies an HTTP request throughout its lifetime.
 */
class HTTPRequest {
    public boolean http11;
    public Map<String, String> headers = new HashMap<String, String>();
    public String destination;
    public Method method;
    public int response;
    public byte[] body;

    /**
     * This constructor does a lot more than an average constructor, and actually fulfills an important job in its
     * parent function because it is blocking. This means the thread will wait for new data in this function. It will
     * return early if errors in the structure are detected.
     * @param inputStream The stream that the constructor will read from.
     * @throws IOException Will throw IOExceptions because managing the stream is not its job
     */
    public HTTPRequest(InputStream inputStream) throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));

        // Method is parsed differently from headers, is always on first line; parse it first
        ParseMethod(bufReader.readLine());
        if (response == 400) {
            return;
        }

        // Read until CRLF, all the while joining split lines
        Stack<String> lines = new Stack<>();
        while(true) {
            String line = bufReader.readLine();
            if(line == "") {
                break;
            }
            if(line.matches("^ .*")) {
                try {
                    lines.push(lines.pop() + line);
                } catch (Exception e) {
                    response = 400;
                    return;
                }
            }
            lines.push(line);
        }

        // Take the strings apart and extract headers to fill the Map
        for ( String str : lines ) {
            String[] splits = str.split(":", 2);
            headers.put( splits[0], splits[1].trim());
        }

        if (method == Method.POST || method == Method.PUT) {
            if ( !headers.containsKey("Content-Length")) {
                response = 400;
                return;
            }
            body = new byte[Integer.valueOf(headers.get("Content-Length"))];
            int bytes_read = inputStream.read(body);
            if (bytes_read != body.length) {
                response = 400;
            }
        }
    }

    /**
     * Parses the first line of an HTTP requests and fills in the object's fields.
     * @param line The initial line of the request, passed by the constructor.
     */
    private void ParseMethod(String line) {
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "GET": method = Method.GET; break;
            case "HEAD": method = Method.HEAD; break;
            case "PUT": method = Method.PUT; break;
            case "POST": method = Method.POST; break;
            default:
                response = 400;
                return;
        }
        this.destination = parts[1];
        switch (parts[2]) {
            case "HTTP/1.0":
                break;
            case "HTTP/1.1":
                http11 = true;
                break;
            default:
                response = 400;
        }
    }
}

enum Method {
    HEAD,
    GET,
    PUT,
    POST
}
