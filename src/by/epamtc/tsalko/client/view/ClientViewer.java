package by.epamtc.tsalko.client.view;

import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Text;

import java.io.*;
import java.util.List;

public class ClientViewer {

    private static final String TYPE_ENDING = "--end--";

    public void printWelcomeMessage(ObjectInputStream objectIn) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(objectIn), 1);
        String line;
        while (true) {
            line = reader.readLine();
            if (line.contains(TYPE_ENDING)) {
                break;
            }
            printMessage(line);
        }
    }

    public void printFormattedText(Text text) {
        List<Component> components = text.getText();
        StringBuilder buff = new StringBuilder();

        buff.append("_____________________________________________________________\n");
        for (Component c : components) {
            buff.append(c.getContent()).append("\n");
        }
        buff.append("_____________________________________________________________\n");

        System.out.println(buff.toString());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
