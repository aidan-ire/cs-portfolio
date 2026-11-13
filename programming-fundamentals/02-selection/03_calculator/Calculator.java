import java.util.*;

public class Calculator{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input two numbers: ");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        System.out.print("Please input an operator (+, -, *, /): ");
        char op = sc.next().charAt(0);

        if(op == '+'){
            System.out.println("Result: " + (a + b));
        }else if(op == '-'){
            System.out.println("Result: " + (a - b));
        }else if(op == '*'){
            System.out.println("Result: " + (a * b));
        }else if(op == '/'){
            if(b == 0){
                System.out.println("Cannot divide by zero");   // edge case
            }else{
                System.out.println("Result: " + (a / b));
            }
        }else{
            System.out.println("Unknown operator");
        }

        sc.close();
    }
}
