import java.util.*;

public class Strings2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine().trim();

        int space = sentence.indexOf(' ');
        String first = sentence;
        if(space != -1) first = sentence.substring(0, space);   // no space means the whole input is one word

        System.out.println("First word: " + first);
        System.out.println("Contains \"java\": " + sentence.toLowerCase().contains("java"));
        System.out.println("Spaces replaced: " + sentence.replace(' ', '_'));

        sc.close();
    }
}
