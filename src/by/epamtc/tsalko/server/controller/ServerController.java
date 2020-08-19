package by.epamtc.tsalko.server.controller;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.server.service.TextService;
import by.epamtc.tsalko.server.service.exception.ServiceException;
import by.epamtc.tsalko.server.service.impl.TextServiceImpl;
import by.epamtc.tsalko.server.view.MessageSender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private static final int PORT = 3575;

    private static MessageSender messageSender;
    private static TextService textService;

    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static InputStream in;
    private static OutputStream out;
    private static ObjectOutputStream objectOut;

    public static void main(String[] args) {
        startServer();
    }

    private static void startServer() {
        System.out.println("Server started");
        try {
            try {
                messageSender = new MessageSender();
                textService = new TextServiceImpl();

                serverSocket = new ServerSocket(PORT);
                clientSocket = serverSocket.accept();

                System.out.println("Connection established");
                try {
                    in = clientSocket.getInputStream();
                    out = clientSocket.getOutputStream();

                    // Sending welcome
//                    System.out.println("Sending welcome message");
//                    messageSender.sendWelcomeMessage(out);


                    sendSerializableText();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                serverSocket.close();
            }
        } catch (IOException | ServiceException e) {
            // TODO: log
        }
    }

    private static void sendSerializableText() throws IOException {
        objectOut = new ObjectOutputStream(out);
        String requestLine = readEditType();
        Text formattedText = null;
        System.out.println("requestLine: " + requestLine);
        String requestType = requestLine.replaceAll("[\\D\\s]", "");
        System.out.println("requestType: " + requestType);

        Text text = textService.createText(readAllText());
        System.out.println("Text is parsed");
        if (requestType.equals("1")) {
            System.out.println("Started requestType 1");
            formattedText = textService.formSentencesAscending(text);
        } else if (requestType.equals("2")) {
            System.out.println("Started requestType 2");
            formattedText = textService.formSentenceOppositeReplacementFirstLastWords(text);
        } else {
            System.out.println("Error requestType");
            messageSender.sendError(out);
        }

        if (formattedText != null) {
            System.out.println("Started serialize");
            objectOut.writeObject(formattedText);
            objectOut.flush();
        }
    }

    private static String readEditType() throws IOException {
        String editType = "";
        while (true) {
            editType += new String(in.readNBytes(1));
            if (editType.contains("\n")) {
                break;
            }
        }
        return editType;
    }

    private static String readAllText() throws IOException {
        StringBuilder builder = new StringBuilder();
        byte[] buff = new byte[128];
        while (in.available() > 0) {
            in.read(buff);
            builder.append(new String(buff));
        }
        System.out.println("Вышли из чтения текста из потока");
        System.out.println("\n----------------------------------------------------\n");
        System.out.println(builder);
        System.out.println("\n----------------------------------------------------\n");
        return builder.toString();
    }
}
