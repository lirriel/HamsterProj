package com.example.server;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static com.example.server.HamsterServerListener.logger;

final class RequestHandler implements Runnable {
    private Socket clientSocket;
    private HamsterServer server;
    private Scanner scanner;
    private PrintWriter printWriter;

    public RequestHandler(Socket clientSocket, HamsterServer server) throws IOException {
        // TODO: наполнить обработкой
        this.clientSocket = clientSocket;
        this.server = server;

        scanner = new Scanner(clientSocket.getInputStream());
        printWriter = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        boolean isClosing = false;
        try {
            while (!isClosing) {
                String incomingMsg = scanner.nextLine();
                String[] params = new Gson().fromJson(incomingMsg, String[].class);
                isClosing = handleOperation(params);
            }

        } catch (Exception ex) {
            logger.error("Error while processing client connection: " + ex.getMessage());
        }
    }

    private boolean handleOperation(String[] params) throws Exception {
        String operation = params[0];
        boolean isClosing = false;

        if (operation.equals("init")) {
            String[] configuration = server.statistics.insert(params);
            isClosing = false;
        } else if (operation.equals("request")) {
            //TODO
            isClosing = false;
        } else if (operation.equals("exit")) {
            server.statistics.insert(params);
            isClosing = true;
        }

        return isClosing;
    }
}