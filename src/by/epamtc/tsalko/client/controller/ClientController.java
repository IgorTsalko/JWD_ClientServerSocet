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

    private static final int WAITING_TIME = 500;
    private static final String TYPE_ENDING = "\n--end--\n";

    private final BufferedReader consoleReader;
    private final ClientViewer clientViewer;
    private final ReaderFromFile readerFromFile;

    private Text desiredText;

    private Socket clientSocket;
    private ObjectInputStream objectIn;
    private OutputStream out;

    public ClientController() {
        consoleReader = new BufferedReader(new InputStreamReader(System.in));
        clientViewer = new ClientViewer();
        readerFromFile = new ReaderFromFile();
    }

    public void start(final String HOST, final int PORT) {
        try {
            try {
                clientSocket = new Socket(HOST, PORT);
                logger.info("Client started");

                objectIn = new ObjectInputStream(clientSocket.getInputStream());
                out = clientSocket.getOutputStream();

                clientViewer.printWelcomeMessage(objectIn);
                logger.info("Welcome message is printed");

                sendRequest();
                logger.info("Request is sent");

                deserializeText();
                logger.info("Modified text is deserialized");

                clientViewer.printFormattedText(desiredText);
                logger.info("Modified text is printed");
            } finally {
                clientSocket.close();
                logger.info("Client is closed");
            }
        } catch (IOException | DAOException | ClassNotFoundException e) {
            logger.error(e);
        }
    }

    private void sendRequest() throws IOException, DAOException {
        String typeOfEdit = consoleReader.readLine() + "\n";
        String allText = readerFromFile.readAllText();
        logger.info("Read all text from file");

        String request = typeOfEdit + allText + TYPE_ENDING;

        out.write(request.getBytes());
        out.flush();
    }

    private void deserializeText() throws IOException, ClassNotFoundException {
        // waiting for data from the server
            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        logger.info("Deserialize started");
        desiredText = (Text) objectIn.readObject();
        logger.info("Deserialize completed");
    }
}
