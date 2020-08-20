package by.epamtc.tsalko.server.controller;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.server.service.TextService;
import by.epamtc.tsalko.server.service.exception.ServiceException;
import by.epamtc.tsalko.server.service.impl.TextServiceImpl;
import by.epamtc.tsalko.server.view.MessageSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private static final Logger logger = LogManager.getLogger(ServerController.class);

    private static final int PORT = 3575;

    private static MessageSender messageSender;
    private static TextService textService;

    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static InputStream in;
    private static OutputStream out;
    private static ObjectOutputStream objectOut;
    private static BufferedReader reader;

    public static void main(String[] args) {
        try {
            try {
                messageSender = new MessageSender();
                textService = new TextServiceImpl();

                serverSocket = new ServerSocket(PORT);
                logger.info("Server started");

                clientSocket = serverSocket.accept();
                logger.info("Connection established");

                try {
                    in = clientSocket.getInputStream();
                    out = clientSocket.getOutputStream();
                    objectOut = new ObjectOutputStream(out);
                    reader = new BufferedReader(new InputStreamReader(in));

                    logger.info("Sending welcome message");
                    messageSender.sendWelcomeMessage(objectOut);

                    sendSerializableText();
                } finally {
                    objectOut.close();
                    in.close();
                    out.close();
                    clientSocket.close();
                    logger.info("Server is closed");
                }
            } finally {
                serverSocket.close();
            }
        } catch (IOException | ServiceException e) {
            logger.error(e);
        }
    }

    private static void sendSerializableText() throws IOException {
        Text formattedText = null;

        String requestType = readEditType().strip();
        logger.info("Read request type");

        String allText = readAllText();
        logger.info("Read allText from stream");

        Text text = textService.createText(allText);
        logger.info("Text is parsed");

        if (requestType.equals("1")) {
            formattedText = textService.formSentencesAscending(text);
        } else if (requestType.equals("2")) {
            formattedText = textService.formSentenceOppositeReplacementFirstLastWords(text);
        } else {
            logger.error("Invalid edit code");
        }

        if (formattedText != null) {
            logger.info("Started serialize");
            objectOut.writeObject(formattedText);
            logger.info("Text is serialized and sent");
        } else {
            logger.error("Text didn't serialize");
        }
    }

    private static String readEditType() throws IOException {
        logger.info("Start to read edit type");
        return reader.readLine();
    }

    private static String readAllText() throws IOException {
        logger.info("Start to read text from stream");
        StringBuilder buff = new StringBuilder();
        String line;
        while (true) {
            line = reader.readLine();
            if (line.contains("--end--")) {
                break;
            }
            buff.append(line).append("\n");
        }
        return buff.toString();
    }
}
