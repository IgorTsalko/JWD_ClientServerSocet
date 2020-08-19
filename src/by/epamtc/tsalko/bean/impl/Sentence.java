package by.epamtc.tsalko.bean.impl;

import by.epamtc.tsalko.bean.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements Component {

    private final List<Component> partsOfSentence;

    public Sentence() {
        partsOfSentence = new ArrayList<>();
    }

    @Override
    public String getContent() {
        StringBuilder buff = new StringBuilder();

        for (Component c : partsOfSentence) {
            buff.append(c.getContent());
        }

        return buff.toString();
    }

    public List<Component> getPartsOfSentence() {
        return partsOfSentence;
    }

    public void addPart(Component component) {
        partsOfSentence.add(component);
    }

    public void addPart(int index, Component component) {
        partsOfSentence.add(index, component);
    }

    public void removePart(Component component) {
        partsOfSentence.remove(component);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return this.partsOfSentence.equals(sentence.partsOfSentence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partsOfSentence);
    }
}
