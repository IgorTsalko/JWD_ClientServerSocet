package by.epamtc.tsalko.client.view;

import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ClientViewer {

    public void printWelcomeMessage(InputStream in) throws IOException {
        // Ждем пока точно отправят данные
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        byte[] buff = new byte[128];
        while (in.available() > 0) {
            in.read(buff);
            System.out.println(new String(buff));
        }
        in.reset();
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
