import java.util.*;

public class VowelsRecursion{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String str = sc.nextLine();

        System.out.println("Number of vowels: " + countVowels(str, 0));

        sc.close();
    }

    /*
    counts the vowels from index i to the end: 1 or 0 for the character at i,
    plus the count for everything after it. the base case is running off the end.
    passing an index instead of making substrings means no new strings are created
    */
    public static int countVowels(String str, int i){
        if(i == str.length()) return 0;    // base case

        int rest = countVowels(str, i + 1);
        if(isVowel(str.charAt(i))) return 1 + rest;
        return rest;
    }

    public static boolean isVowel(char c){
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
