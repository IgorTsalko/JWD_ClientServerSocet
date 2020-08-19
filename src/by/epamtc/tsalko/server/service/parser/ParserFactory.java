package by.epamtc.tsalko.server.service.parser;

import by.epamtc.tsalko.server.service.exception.ServiceException;

public class ParserFactory {

    private static ComponentParser componentParser;
    private static SentenceParser sentenceParser;
    private static PartOfSentenceParser partOfSentenceParser;

    private ParserFactory() {}

    public static ComponentParser getComponentParser() throws ServiceException {
        if (componentParser == null) {
            componentParser = new ComponentParser();
        }
        return componentParser;
    }

    public static SentenceParser getSentenceParser() throws ServiceException {
        if (sentenceParser == null) {
            sentenceParser = new SentenceParser();
        }
        return sentenceParser;
    }

    public static PartOfSentenceParser getWordParser() throws ServiceException {
        if (partOfSentenceParser == null) {
            partOfSentenceParser = new PartOfSentenceParser();
        }
        return partOfSentenceParser;
    }
}
