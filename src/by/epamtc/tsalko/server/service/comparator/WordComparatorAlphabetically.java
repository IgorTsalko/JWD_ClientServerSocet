package by.epamtc.tsalko.server.service.comparator;



import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Word;

import java.util.Comparator;

public class WordComparatorAlphabetically implements Comparator<Component> {

    @Override
    public int compare(Component o1, Component o2) {
        return ((Word) o1).getWord().compareTo(((Word) o2).getWord());
    }
}
