package by.epamtc.tsalko.bean.impl;

import by.epamtc.tsalko.bean.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Text implements Component {

    private final List<Component> text;

    public Text() {
        this.text = new ArrayList<>();
    }

    public Text(List<Component> components) {
        text = components;
    }

    public List<Component> getText() {
        return text;
    }

    public void addTextComponent(Component component) {
        text.add(component);
    }

    @Override
    public String getContent() {
        StringBuilder buff = new StringBuilder();

        for (Component c : text) {
            buff.append(c.getContent());
        }

        return buff.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return text.equals(text1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
