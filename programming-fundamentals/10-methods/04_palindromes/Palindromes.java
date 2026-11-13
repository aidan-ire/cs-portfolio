import java.util.*;

public class Palindromes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word: ");
        String word = sc.next();

        if(isPalindrome(word)){
            System.out.println(word + " is a palindrome");
        }else{
            System.out.println(word + " is not a palindrome");
        }

        sc.close();
    }

    // compares characters from both ends moving inwards, ignoring case.
    // stops at the middle, so a word of length n takes at most n/2 comparisons
    public static boolean isPalindrome(String word){
        String lower = word.toLowerCase();
        int i = 0;
        int j = lower.length() - 1;

        while(i < j){
            if(lower.charAt(i) != lower.charAt(j)) return false;
            i++;
            j--;
        }

        return true;
    }
}
