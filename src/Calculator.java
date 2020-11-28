import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Enter expression:");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        List<Token> tokens = Tokenizer.tokenize(expression);
        PrintVisitor printVisitor = new PrintVisitor();
        printVisitor.visit(tokens);

        ParserVisitor parserVisitor = new ParserVisitor();
        parserVisitor.visit(tokens);
        tokens = parserVisitor.getTokensInRPN();
        printVisitor.visit(tokens);

    }
}
