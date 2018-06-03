package com.example.hamsterserveraccesslibrary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

public class HamsterServerAccess {
    private static Logger logger = LogManager.getLogger("hsa-logger");
    private static String serverIP = "104.45.30.66";
    private static int port = 9090;

    private URL url;
    private boolean isConnected;
    private Socket socket;

    public void connect() throws Exception {
        socket = new Socket(serverIP, port);
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write("ХУЙ");
            writer.close();
        } catch (Exception ex) {
            throw new Exception(String.format("Connection failed. Error occurred: %s"));
        }
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public void disconnect() throws IOException {
        socket.close();
        isConnected = false;
    }


}
