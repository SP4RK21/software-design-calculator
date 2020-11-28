package tokens;

import visitors.TokenVisitor;

public class OperationToken implements Token {
    private OperationType operationType;

    OperationToken(OperationType type) {
        operationType = type;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public int priority() {
        switch (operationType) {
            case PLUS:
            case MINUS:
                return 0;
            case MUL:
            case DIV:
                return 1;
            default:
                return -1;
        }
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
