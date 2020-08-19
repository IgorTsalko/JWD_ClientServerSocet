package by.epamtc.tsalko.bean.impl;

import by.epamtc.tsalko.bean.Component;

import java.util.Objects;

public class PunctuationMark implements Component {
    private final String punctuationMark;

    public PunctuationMark(String punctuationMark) {
        this.punctuationMark = punctuationMark;
    }

    @Override
    public String getContent() {
        return punctuationMark;
    }

    public String getPunctuationMark() {
        return punctuationMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PunctuationMark that = (PunctuationMark) o;
        return punctuationMark.equals(that.punctuationMark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punctuationMark);
    }
}
