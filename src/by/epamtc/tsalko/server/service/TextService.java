package by.epamtc.tsalko.server.service;


import by.epamtc.tsalko.bean.impl.Text;

public interface TextService {

    Text createText(String allText);

    // №2
    Text formSentencesAscending(Text text);

    // №5
    Text formSentenceOppositeReplacementFirstLastWords(Text text);
}
