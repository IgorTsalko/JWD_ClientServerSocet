package by.epamtc.tsalko.client.controller;

import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.client.model.dao.ReaderFromFile;
import by.epamtc.tsalko.client.model.dao.exception.DAOException;
import by.epamtc.tsalko.client.view.ClientViewer;

import java.io.*;
import java.net.Socket;

public class ClientController {

    private static final String HOST = "localhost";
    private static final int PORT = 3575;

    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    private static ClientViewer clientViewer = new ClientViewer();
    private static ReaderFromFile readerFromFile = new ReaderFromFile();
    private static ObjectInputStream objectIn;

    private static Text desiredText;

    private static Socket clientSocket;
    private static InputStream in;
    private static OutputStream out;

    public static void main(String[] args) {
        startClient();
    }

    public static void startClient() {
        System.out.println("Client started");
        try {
            try {
                clientSocket = new Socket(HOST, PORT);

                in = clientSocket.getInputStream();
                out = clientSocket.getOutputStream();

//                clientViewer.printWelcomeMessage(in);

                sendRequest();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                deserializeText();

                System.out.println("Start showing result");
                clientViewer.printFormattedText(desiredText);
            } finally {
                clientSocket.close();
            }
        } catch (IOException | DAOException | ClassNotFoundException e) {
            // TODO: log
            e.printStackTrace();
        }
    }

    private static void sendRequest() throws IOException, DAOException {
        BufferedWriter senderToServer = new BufferedWriter(new OutputStreamWriter(out));
        clientViewer.printMessage("Enter your choice:");
        senderToServer.write(consoleReader.readLine() + "\n\n");

        System.out.println("Sending file to server");
        senderToServer.write(readerFromFile.readAllText());
        senderToServer.flush();
    }

    private static void deserializeText() throws IOException, ClassNotFoundException {
        System.out.println("Deserialize started");
        objectIn = new ObjectInputStream(in);
        desiredText = (Text) objectIn.readObject();
    }
}
