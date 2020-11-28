package visitors;

import tokens.BraceToken;
import tokens.NumberToken;
import tokens.OperationToken;
import tokens.Token;

import java.util.List;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(BraceToken token);
    void visit(OperationToken token);
    void visit(List<Token> tokens);
}