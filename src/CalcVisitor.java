import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalcVisitor implements TokenVisitor {

    private Integer result = null;
    private Stack<Integer> tokenStack = new Stack<>();

    public int getResult() {
        return result;
    }

    @Override
    public void visit(NumberToken token) {
        tokenStack.push(token.getValue());
    }

    @Override
    public void visit(BraceToken token) {
        throw new IllegalStateException("Can't have braces when calculating in RPN");
    }

    @Override
    public void visit(OperationToken token) {
        if (tokenStack.size() < 2) {
            throw new IllegalStateException("Should have two arguments for operation");
        }
        int first = tokenStack.pop();
        int second = tokenStack.pop();
        switch (token.getOperationType()) {
            case PLUS:
                tokenStack.push(first + second);
                break;
            case MINUS:
                tokenStack.push(first - second);
                break;
            case MUL:
                tokenStack.push(first * second);
                break;
            case DIV:
                tokenStack.push(second / first);
                break;
        }
    }

    @Override
    public void visit(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        if (tokenStack.size() != 1) {
            throw new IllegalStateException("Wrong format of RPN");
        }
        result = tokenStack.pop();
    }
}
