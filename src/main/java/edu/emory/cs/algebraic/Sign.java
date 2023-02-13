package edu.emory.cs.algebraic;
/** @author Jinho D. Choi */
public enum Sign {
    POSITIVE('+'),
    NEGATIVE('-');

    private final char value;

    Sign(char value) {
        this.value = value;
    }

    /** @return the value of the corresponding item. */
    public char value() {
        return value;
    }
}