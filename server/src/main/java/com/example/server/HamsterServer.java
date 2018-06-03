package com.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import model.StatisticsDB;

import static com.example.server.HamsterServerListener.logger;

public class HamsterServer {
    protected static int PORT = 9090;
    public StatisticsDB statistics;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    protected HamsterServer() throws IOException, ClassNotFoundException, SQLException {
        serverSocket = new ServerSocket(PORT);
        statistics = new StatisticsDB();
    }

    protected void startServer() throws IOException {
        logger.info("Server started listening on port " + PORT);

        while (true) {
            clientSocket = serverSocket.accept();
            RequestHandler clientRqHandler = new RequestHandler(clientSocket, this);
            new Thread(clientRqHandler).run();
        }
    }
}
