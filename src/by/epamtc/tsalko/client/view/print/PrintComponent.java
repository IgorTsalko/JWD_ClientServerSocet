package by.epamtc.tsalko.client.view.print;


import by.epamtc.tsalko.bean.Component;

import java.util.List;

public class PrintComponent {

    public void print(List<? extends Component> components) {
        StringBuilder buff = new StringBuilder();

        for (Component c : components) {
            buff.append(c.getContent()).append("\n");
        }

        System.out.println(buff.toString());
    }
}
