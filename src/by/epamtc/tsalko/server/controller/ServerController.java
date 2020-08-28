package by.epamtc.tsalko.server.controller;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.server.service.TextService;
import by.epamtc.tsalko.server.service.exception.ServiceException;
import by.epamtc.tsalko.server.service.impl.TextServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ServerController {
    private static final Logger logger = LogManager.getLogger(ServerController.class);

    private static final String TYPE_ENDING = "--end--";

    private TextService textService;

    private BufferedReader requestReader;
    private ObjectOutputStream objectOut;

    public ServerController() throws ServiceException {
        textService = new TextServiceImpl();
    }

    public void start(final Socket clientSocket) {
        try {
            requestReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            objectOut = new ObjectOutputStream(clientSocket.getOutputStream());

            sendWelcomeMessage(objectOut);
            logger.info("Sent welcome message");

            String editNumber = readEditNumber();
            logger.info("Read request type");

            String textData = readTextData();
            logger.info("Read text data from stream");

            Text text = textService.createText(textData);
            logger.info("Text is parsed");

            Text modifiedText = modifyText(text, editNumber);
            logger.info("Text is modified");

            if (modifiedText != null) {
                logger.info("Started serialize");
                objectOut.writeObject(modifiedText);
                logger.info("Text is serialized and sent");
            } else {
                logger.warn("Text didn't serialize, invalid error code");
                sendErrorMessage();
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

    private void sendWelcomeMessage(ObjectOutputStream objectOut) throws IOException {
        StringBuilder welcomeMessage = new StringBuilder()
                .append("You can do one of this edit\n")
                .append("Enter 1, if you want to form sentences in ascending order\n")
                .append("Enter 2, if you want to form sentences with the replacement\n")
                .append("of the first and last words in places\n")
                .append("Enter your choice:\n")
                .append("--end--\n");

        objectOut.write(welcomeMessage.toString().getBytes());
        objectOut.flush();
    }

    private void sendErrorMessage() throws IOException {
        objectOut.write("You entered invalid edit code".getBytes());
    }

    private String readEditNumber() throws IOException {
        return requestReader.readLine();
    }

    private String readTextData() throws IOException {
        logger.info("Start to read text from stream");
        StringBuilder buff = new StringBuilder();
        String line;
        while (true) {
            line = requestReader.readLine();
            if (line.contains(TYPE_ENDING)) {
                break;
            }
            buff.append(line).append("\n");
        }
        return buff.toString();
    }

    private Text modifyText(Text text, String editNumber) {
        if (editNumber.equals("1")) {
            return textService.formSentencesAscending(text);
        } else if (editNumber.equals("2")) {
            return textService.formSentenceOppositeReplacementFirstLastWords(text);
        } else {
            return null;
        }
    }
}
