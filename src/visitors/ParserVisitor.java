package visitors;

import tokens.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {

    private List<Token> tokens = new ArrayList<>();
    private Stack<Token> tokenStack = new Stack<>();

    public List<Token> getTokensInRPN() {
        return tokens;
    }

    @Override
    public void visit(NumberToken token) {
        tokens.add(token);
    }

    @Override
    public void visit(BraceToken token) {
        switch (token.getBraceType()) {
            case LEFT_BRACE:
                tokenStack.push(token);
                break;
            case RIGHT_BRACE:
                while (!tokenStack.empty()) {
                    Token topToken = tokenStack.peek();
                    if ((topToken instanceof BraceToken) && ((BraceToken) topToken).getBraceType() == BraceType.LEFT_BRACE) {
                        tokenStack.pop();
                        break;
                    } else {
                        tokens.add(topToken);
                        tokenStack.pop();
                    }
                }
                break;
        }
    }

    @Override
    public void visit(OperationToken token) {
        while (!tokenStack.empty()) {
            Token topToken = tokenStack.peek();
            if (topToken instanceof OperationToken && token.priority() <= ((OperationToken) topToken).priority()) {
                tokens.add(topToken);
                tokenStack.pop();
            } else {
                break;
            }
        }
        tokenStack.push(token);
    }

    @Override
    public void visit(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        while (!tokenStack.empty()) {
            Token topToken = tokenStack.peek();
            if (topToken instanceof OperationToken) {
                this.tokens.add(topToken);
                tokenStack.pop();
            } else {
                throw new IllegalArgumentException("Incorrect expression or unmatched brackets");
            }
        }
    }
}
