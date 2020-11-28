package tokens;

import visitors.TokenVisitor;

public class NumberToken implements Token {
    private int value;

    NumberToken(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("NUMBER(%d)", value);
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
