package by.epamtc.tsalko.client.view;

import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Text;

import java.io.*;
import java.util.List;

public class ClientViewer {

    public void printWelcomeMessage(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in), 1);
        String line;
        while (true) {
            line = reader.readLine();
            if (line.contains("--end--")) {
                break;
            }
            printMessage(line);
        }
    }

    public void printFormattedText(Text text) {
        List<Component> components = text.getText();
        StringBuilder buff = new StringBuilder();

        for (Component c : components) {
            buff.append(c.getContent()).append("\n");
        }

        System.out.println(buff.toString());
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
