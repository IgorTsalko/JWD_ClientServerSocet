package by.epamtc.tsalko.server.service.impl;


import by.epamtc.tsalko.bean.Component;
import by.epamtc.tsalko.bean.impl.Sentence;
import by.epamtc.tsalko.bean.impl.Text;
import by.epamtc.tsalko.bean.impl.Word;
import by.epamtc.tsalko.server.service.TextService;
import by.epamtc.tsalko.server.service.comparator.*;
import by.epamtc.tsalko.server.service.exception.ServiceException;
import by.epamtc.tsalko.server.service.parser.*;

import java.util.ArrayList;
import java.util.List;

public class TextServiceImpl implements TextService {

    private final ComponentParser componentParser;

    private SentenceComparatorByWord sentenceComparatorByWord = new SentenceComparatorByWord();
    private WordComparatorAlphabetically wordComparatorAlphabetically = new WordComparatorAlphabetically();

    public TextServiceImpl() throws ServiceException {
        componentParser = ParserFactory.getComponentParser();
    }

    @Override
    public Text createText(String allText) {
        return componentParser.createText(allText);
    }

    @Override
    public Text formSentencesAscending(Text text) {
        List<Component> sentences = getListSentences(text);
        sentences.sort(sentenceComparatorByWord);
        return new Text(sentences);
    }

    @Override
    public Text formSentenceOppositeReplacementFirstLastWords(Text text) {
        List<Component> sentences = getListSentences(text);
        List<Component> formedSentences = new ArrayList<>();

        for (Component s : sentences) {
            Sentence editedSentence = new Sentence();

            Word firstWord = null;
            Word lastWord = null;
            for (Component c : ((Sentence) s).getPartsOfSentence()) {
                if (c.getClass().equals(Word.class)) {
                    Word word = (Word) c;
                    if (firstWord == null) {
                        firstWord = word;
                    } else {
                        lastWord = word;
                    }
                }
                editedSentence.addPart(c);
            }

            if (firstWord != null && lastWord != null) {
                editedSentence.removePart(firstWord);
                editedSentence.removePart(lastWord);
                editedSentence.addPart(((Sentence) s).getPartsOfSentence().indexOf(firstWord), lastWord);
                editedSentence.addPart(((Sentence) s).getPartsOfSentence().indexOf(lastWord), firstWord);

                formedSentences.add(editedSentence);
            } else {
                formedSentences.add(s);
            }
        }

        return new Text(formedSentences);
    }

    private List<Component> getListSentences(Text text) {
        List<Component> sentences = new ArrayList<>();

        for (Component component : text.getText()) {
            if (component.getClass().equals(Sentence.class)) {
                Sentence sentence = (Sentence) component;
                sentences.add(sentence);
            }
        }

        return sentences;
    }

}
