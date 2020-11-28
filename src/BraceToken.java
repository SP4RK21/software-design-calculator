public class BraceToken implements Token {

    private BraceType braceType;

    public BraceToken(BraceType type) {
        braceType = type;
    }

    public BraceType getBraceType() {
        return braceType;
    }

    @Override
    public String toString() {
        switch (braceType) {
            case LEFT_BRACE:
                return "LEFT";
            case RIGHT_BRACE:
                return "RIGHT";
            default:
                return "UNKNOWN_BRACE";
        }
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}

