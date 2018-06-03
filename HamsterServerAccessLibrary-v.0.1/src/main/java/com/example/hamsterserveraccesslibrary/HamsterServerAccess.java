package com.example.hamsterserveraccesslibrary;

import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sun.rmi.runtime.Log;

public class HamsterServerAccess {
    private static Logger logger = LogManager.getLogger("hsa-logger");
    private static String serverIP = "104.45.30.66";
    private static int port = 9090;

    private URL url;
    private boolean isConnected;
    private HttpURLConnection connection;

    public void connect() throws Exception {
        try {
            url = new URL(serverIP);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            isConnected = true;
        } catch(Exception ex){
            isConnected = false;
            throw new Exception(String.format("Connection failed. Error occurred: %s"));
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void disconnect() {
        connection.disconnect();
        isConnected = false;
    }
}
