package by.epamtc.tsalko.server.start;

import by.epamtc.tsalko.server.controller.ServerController;
import by.epamtc.tsalko.server.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart {

    private static final Logger logger = LogManager.getLogger(ServerStart.class);

    private static final int PORT = 3575;

    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            logger.info("Server started");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Connection established");

                ServerController serverController = new ServerController();
                serverController.start(clientSocket);
                clientSocket.close();
                logger.info("ClientSocket is closed");
            }
        } catch (ServiceException | IOException e) {
            logger.error(e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
        logger.info("Server is closed");
    }
}
