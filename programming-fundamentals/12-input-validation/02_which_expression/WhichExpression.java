import java.util.*;

public class WhichExpression{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = readInt(sc, "Please input a: ");
        int b = readInt(sc, "Please input b: ");
        int c = readInt(sc, "Please input c: ");

        int e1 = a + b * c;
        int e2 = a * b + c;
        int e3 = (a + b) * c;

        System.out.println("a + b * c   = " + e1);
        System.out.println("a * b + c   = " + e2);
        System.out.println("(a + b) * c = " + e3);

        // >= so that on a tie the first expression listed is reported
        if(e1 >= e2 && e1 >= e3){
            System.out.println("a + b * c gives the largest result");
        }else if(e2 >= e3){
            System.out.println("a * b + c gives the largest result");
        }else{
            System.out.println("(a + b) * c gives the largest result");
        }

        sc.close();
    }

    // hasNextInt checks the next token without using it up, so anything
    // that isn't a whole number is thrown away with next() and the user is asked again
    public static int readInt(Scanner sc, String prompt){
        System.out.print(prompt);
        while(!sc.hasNextInt()){
            sc.next();
            System.out.print("That is not a whole number. " + prompt);
        }
        return sc.nextInt();
    }
}
