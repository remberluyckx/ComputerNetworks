package be.rember_arthur.computernetworks.lab02.server;

import java.io.*;
import java.util.regex.Pattern;

/**
 * HTTPRequest is at its heart a DTO (Data Transfer Object) and as such all values are public final and the class
 * exposes no "getters" or "setters".
 */
class HTTPRequest {
    public final boolean http11;
    public final boolean connection_close;
    public final boolean bad_request;
    public final String destination;
    public final String host;
    public final Method method;
    public final byte[] body;

    private static Pattern getPattern = Pattern.compile("^GET.*");
    private static Pattern headPattern = Pattern.compile("^HEAD.*");
    private static Pattern postPattern = Pattern.compile("^HEAD.*");
    private static Pattern putPattern = Pattern.compile("^PUT.*");



    /**
     * Reads the input stream from the socket and blocks until it has parsed a full HTTP request.
     */
    public HTTPRequest(InputStream inputStream) throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
        while(true) {
            String newLine = bufReader.readLine();

        }
    }
}

enum Method {
    HEAD,
    GET,
    PUT,
    POST
}
