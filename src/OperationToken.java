public class OperationToken implements Token {
    private OperationType operationType;

    public OperationToken(OperationType type) {
        operationType = type;
    }

    @Override
    public String toString() {
        switch (operationType) {
            case PLUS:
                return "PLUS";
            case MINUS:
                return "MINUS";
            case MUL:
                return "MUL";
            case DIV:
                return "DIV";
            default:
                return "UNKNOWN_OPERATION";
        }
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
