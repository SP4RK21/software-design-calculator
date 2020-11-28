import tokens.Token;
import tokens.Tokenizer;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.util.List;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Enter expression:");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        List<Token> tokens = Tokenizer.tokenize(expression);
        PrintVisitor printVisitor = new PrintVisitor();
        System.out.println("Tokenized expression:");
        printVisitor.visit(tokens);

        ParserVisitor parserVisitor = new ParserVisitor();
        parserVisitor.visit(tokens);
        tokens = parserVisitor.getTokensInRPN();
        System.out.println("RPN expression:");
        printVisitor.visit(tokens);

        CalcVisitor calcVisitor = new CalcVisitor();
        calcVisitor.visit(tokens);
        System.out.println("Result:");
        System.out.println(calcVisitor.getResult());
    }
}
