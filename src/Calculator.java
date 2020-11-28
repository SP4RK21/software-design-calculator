import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Enter expression:");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        System.out.println(Tokenizer.tokenize(expression));
    }
}
