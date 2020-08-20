package by.epamtc.tsalko.client.controller;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.client.model.ReaderFromFile;
import by.epamtc.tsalko.client.model.exception.DAOException;
import by.epamtc.tsalko.client.view.ClientViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private static final Logger logger = LogManager.getLogger(ClientController.class);

    private static final String HOST = "localhost";
    private static final int PORT = 3575;

    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    private static ClientViewer clientViewer = new ClientViewer();
    private static ReaderFromFile readerFromFile = new ReaderFromFile();

    private static Text desiredText;

    private static Socket clientSocket;
    private static InputStream in;
    private static OutputStream out;
    private static ObjectInputStream objectIn;

    public static void main(String[] args) {
        startClient();
    }

    public static void startClient() {
        try {
            try {
                clientSocket = new Socket(HOST, PORT);
                logger.info("Client started");

                in = clientSocket.getInputStream();
                out = clientSocket.getOutputStream();
                objectIn = new ObjectInputStream(in);

                clientViewer.printWelcomeMessage(objectIn);
                logger.info("Welcome message is printed");

                sendRequest();

                deserializeText();

                clientViewer.printFormattedText(desiredText);
            } finally {
                objectIn.close();
                in.close();
                out.close();
                clientSocket.close();
                logger.info("Client is closed");
            }
        } catch (IOException | DAOException | ClassNotFoundException e) {
            logger.error(e);
        }
    }

    private static void sendRequest() throws IOException, DAOException {
        String typeOfEdit = consoleReader.readLine() + "\n";
        String allText = readerFromFile.readAllText();
        logger.info("Read all text from file");

        String request = typeOfEdit + allText + "\n--end--\n";

        out.write(request.getBytes());
        out.flush();
        logger.info("Send file to server");
    }

    private static void deserializeText() throws IOException, ClassNotFoundException {
        // Ждем пока точно отправят объект
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Deserialize started");
        desiredText = (Text) objectIn.readObject();
        logger.info("Deserialize completed");
    }
}
