import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private static List<Token> tokens = new ArrayList<>();
    private static TokenizerState currentState = new DefaultState();

    public static List<Token> tokenize(String string) {
        tokens = new ArrayList<>();
        currentState = new DefaultState();
        for (char c : string.toCharArray()) {
            currentState.process(c);
        }
        currentState.processEOF();

        return tokens;
    }

    abstract static class TokenizerState {

        abstract void process(char c);
        abstract void processEOF();
    }

    static class NumberState extends TokenizerState {

        int value = 0;

        @Override
        void process(char c) {
            if (c >= '0' && c <= '9') {
                value = value * 10 + (c - '0');
            } else {
                Tokenizer.tokens.add(new NumberToken(value));
                Tokenizer.currentState = new DefaultState();
                Tokenizer.currentState.process(c);
            }
        }

        @Override
        void processEOF() {
            Tokenizer.tokens.add(new NumberToken(value));
        }
    }

    static class DefaultState extends TokenizerState {

        @Override
        void process(char c) {
            if (c == '(') {
                tokens.add(new BraceToken(BraceType.LEFT_BRACE));
            } else if (c == ')') {
                tokens.add(new BraceToken(BraceType.RIGHT_BRACE));
            } else if (c == '+') {
                tokens.add(new OperationToken(OperationType.PLUS));
            } else if (c == '-') {
                tokens.add(new OperationToken(OperationType.MINUS));
            } else if (c == '*') {
                tokens.add(new OperationToken(OperationType.MUL));
            } else if (c == '/') {
                tokens.add(new OperationToken(OperationType.DIV));
            } else if (c >= '0' && c <= '9') {
                currentState = new NumberState();
                Tokenizer.currentState.process(c);
            } else {
                if (!Character.isWhitespace(c)) {
                    throw new IllegalArgumentException("Unknown symbol: " + c);
                }
            }
        }

        @Override
        void processEOF() { /* Do nothing */ }
    }
}