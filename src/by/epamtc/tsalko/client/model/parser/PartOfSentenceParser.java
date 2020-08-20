package by.epamtc.tsalko.client.model.parser;

import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Digit;
import by.epamtc.tsalko.bean.impl.PunctuationMark;
import by.epamtc.tsalko.bean.impl.Word;
import by.epamtc.tsalko.client.model.exception.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PartOfSentenceParser {

    private final String partOfSentenceRegEx;
    private final String wordRegEx;
    private final String digitRegEx;
    private final Pattern pattern;

    public PartOfSentenceParser() throws DAOException {
        partOfSentenceRegEx = PropertyReader.getInstance().getProperty("partOfSentenceRegEx");
        wordRegEx = PropertyReader.getInstance().getProperty("wordRegEx");
        digitRegEx = PropertyReader.getInstance().getProperty("digitRegEx");
        pattern = Pattern.compile(partOfSentenceRegEx);
    }

    public List<Component> parsePartOfSentence(String sentence) {
        List<Component> partsOfSentence = new ArrayList<>();

        Matcher matcher = pattern.matcher(sentence);
        while (matcher.find()) {
            String part = matcher.group().intern();
            if (part.matches(wordRegEx)) {
                partsOfSentence.add(new Word(part));
            } else if (part.matches(digitRegEx)){
                partsOfSentence.add(new Digit(part));
            } else {
                partsOfSentence.add(new PunctuationMark(part));
            }
        }

        return partsOfSentence;
    }
}
