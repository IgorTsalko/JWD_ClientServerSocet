package by.epamtc.tsalko.server.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MessageSender {

    public void sendWelcomeMessage(OutputStream out) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        writer.write("Connection established\n");
        writer.write("-----------------------------------------------------------\n");
        writer.write("You can do one of this edit\n");
        writer.write("Please, chose one\n");
        writer.write("Enter 1, if you want to form sentences in ascending order\n");
        writer.write("Enter 2, if you want to form sentences with the replacement\nof the first and last words in places\n");
        writer.flush();
    }

    public void sendError(OutputStream out) throws IOException {
        out.write("-----------------------------------------------------------\n".getBytes());
        out.write("This command isn't exist\n".getBytes());
        out.write("-----------------------------------------------------------\n".getBytes());
        out.flush();
    }
}
