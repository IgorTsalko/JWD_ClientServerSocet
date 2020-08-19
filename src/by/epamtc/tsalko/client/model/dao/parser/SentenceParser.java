package by.epamtc.tsalko.client.model.dao.parser;

import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Sentence;
import by.epamtc.tsalko.client.model.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser {

    private final String smallTextBlock;
    private final String sentenceRegEx;

    private final PartOfSentenceParser partOfSentenceParser;
    private final Pattern smallTextBlockPattern;
    private final Pattern sentencePattern;

    public SentenceParser() throws DAOException {
        smallTextBlock = PropertyReader.getInstance().getProperty("smallTextBlock");
        sentenceRegEx = PropertyReader.getInstance().getProperty("sentenceRegEx");

        partOfSentenceParser = ParserFactory.getWordParser();
        smallTextBlockPattern = Pattern.compile(smallTextBlock);
        sentencePattern = Pattern.compile(sentenceRegEx);
    }

    public List<Component> parseSentences(String textBlock) {
        List<Component> sentences = new ArrayList<>();

        Matcher smallTextBlockMatcher = smallTextBlockPattern.matcher(textBlock);

        while (smallTextBlockMatcher.find()) {
            Matcher sentenceMatcher = sentencePattern.matcher(smallTextBlockMatcher.group());

            while (sentenceMatcher.find()) {
                Sentence sentenceEntity = new Sentence();

                String sentence = sentenceMatcher.group();
                List<Component> partsOfSentence = partOfSentenceParser.parsePartOfSentence(sentence);

                for (Component c : partsOfSentence) {
                    sentenceEntity.addPart(c);
                }

                sentences.add(sentenceEntity);
            }
        }

        return sentences;
    }
}
