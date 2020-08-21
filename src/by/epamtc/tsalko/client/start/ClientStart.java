package by.epamtc.tsalko.client.start;

import by.epamtc.tsalko.client.controller.ClientController;

public class ClientStart {

    private final static String HOST = "localhost";
    private final static int PORT = 3575;

    public static void main(String[] args) {
        ClientController clientController = new ClientController();
        clientController.start(HOST, PORT);
    }
}
