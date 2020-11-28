import java.util.List;

public class PrintVisitor implements TokenVisitor {
    @Override
    public void visit(NumberToken token) {
        System.out.print(token);
    }

    @Override
    public void visit(BraceToken token) {
        System.out.print(token);
    }

    @Override
    public void visit(OperationToken token) {
        System.out.print(token);
    }

    @Override
    public void visit(List<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            tokens.get(i).accept(this);
            if (i < tokens.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
