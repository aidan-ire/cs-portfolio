import java.util.*;

public class ASCIIDifference{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input two characters: ");
        char a = sc.next().charAt(0);
        char b = sc.next().charAt(0);

        System.out.println(a + " has ASCII value " + (int) a);
        System.out.println(b + " has ASCII value " + (int) b);
        System.out.println("Difference: " + Math.abs(a - b));

        sc.close();
    }
}
