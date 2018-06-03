package com.example.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HamsterServerListener {
    protected static Logger logger = LogManager.getLogger("server-logger");
    private static int counter;

    public static void main(String[] args) {
        counter = 0;
    }
}
