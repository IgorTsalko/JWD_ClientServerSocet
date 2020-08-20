package by.epamtc.tsalko.client.model.parser;

import by.epamtc.tsalko.client.model.exception.DAOException;

public class ParserFactory {

    private static ComponentParser componentParser;
    private static SentenceParser sentenceParser;
    private static PartOfSentenceParser partOfSentenceParser;

    private ParserFactory() {}

    public static ComponentParser getComponentParser() throws DAOException {
        if (componentParser == null) {
            componentParser = new ComponentParser();
        }
        return componentParser;
    }

    public static SentenceParser getSentenceParser() throws DAOException {
        if (sentenceParser == null) {
            sentenceParser = new SentenceParser();
        }
        return sentenceParser;
    }

    public static PartOfSentenceParser getWordParser() throws DAOException {
        if (partOfSentenceParser == null) {
            partOfSentenceParser = new PartOfSentenceParser();
        }
        return partOfSentenceParser;
    }
}
