package by.epamtc.tsalko.server.view;

import java.io.IOException;
import java.io.OutputStream;

public class MessageSender {

    public void sendWelcomeMessage(OutputStream out) throws IOException {
        StringBuilder message = new StringBuilder()
                .append("You can do one of this edit\n")
                .append("Enter 1, if you want to form sentences in ascending order\n")
                .append("Enter 2, if you want to form sentences with the replacement\n")
                .append("of the first and last words in places\n")
                .append("Enter your choice:\n")
                .append("--end--\n");

        out.write(message.toString().getBytes());
        out.flush();
    }

    public void sendError(OutputStream out) throws IOException {
        out.write("-----------------------------------------------------------\n".getBytes());
        out.write("This command isn't exist\n".getBytes());
        out.write("-----------------------------------------------------------\n".getBytes());
        out.flush();
    }
}
