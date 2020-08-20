package by.epamtc.tsalko.server.view;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender {

    public void sendWelcomeMessage(ObjectOutputStream objectOut) throws IOException {
        StringBuilder message = new StringBuilder()
                .append("You can do one of this edit\n")
                .append("Enter 1, if you want to form sentences in ascending order\n")
                .append("Enter 2, if you want to form sentences with the replacement\n")
                .append("of the first and last words in places\n")
                .append("Enter your choice:\n")
                .append("--end--\n");

        objectOut.write(message.toString().getBytes());
        objectOut.flush();
    }
}
