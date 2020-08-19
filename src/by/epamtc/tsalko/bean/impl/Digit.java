package by.epamtc.tsalko.bean.impl;

import by.epamtc.tsalko.bean.Component;

import java.util.Objects;

public class Digit implements Component {
    private final String digit;

    public Digit(String digit) {
        this.digit = digit;
    }

    @Override
    public String getContent() {
        return digit;
    }

    public String getDigit() {
        return digit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digit digit1 = (Digit) o;
        return digit.equals(digit1.digit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digit);
    }
}
