package by.epamtc.tsalko.server.service.comparator;


import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Sentence;
import by.epamtc.tsalko.bean.impl.Word;

import java.util.Comparator;

public class SentenceComparatorByWord implements Comparator<Component> {

    @Override
    public int compare(Component o1, Component o2) {
        int countWordInFirstSentence = 0;
        int countWordInSecondSentence = 0;

        for (Component c : ((Sentence) o1).getPartsOfSentence()) {
            if (c.getClass().equals(Word.class)) {
                countWordInFirstSentence++;
            }
        }

        for (Component c : ((Sentence) o2).getPartsOfSentence()) {
            if (c.getClass().equals(Word.class)) {
                countWordInSecondSentence++;
            }
        }

        return countWordInFirstSentence - countWordInSecondSentence;
    }
}
