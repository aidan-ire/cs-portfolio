import java.util.*;

public class Strings1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word: ");
        String word = sc.next();

        System.out.println("Length: " + word.length());
        System.out.println("First character: " + word.charAt(0));
        System.out.println("Last character: " + word.charAt(word.length() - 1));
        System.out.println("Upper case: " + word.toUpperCase());
        System.out.println("Lower case: " + word.toLowerCase());

        sc.close();
    }
}
