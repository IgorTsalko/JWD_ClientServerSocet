package by.epamtc.tsalko.bean.impl;

import by.epamtc.tsalko.bean.Component;

import java.util.Objects;

public class CodeBlock implements Component {

    private static final long serialVersionUID = 1684L;

    private final String codeBlock;

    public CodeBlock(String codeBlock) {
        this.codeBlock = codeBlock;
    }

    @Override
    public String getContent() {
        return codeBlock;
    }

    public String getCodeBlock() {
        return codeBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeBlock codeBlock1 = (CodeBlock) o;
        return codeBlock.equals(codeBlock1.codeBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeBlock);
    }
}
