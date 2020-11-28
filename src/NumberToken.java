public class NumberToken implements Token {
    private int value;

    public NumberToken(int value) {
        this.value = value;
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
