import java.util.*;

public class PalindromeRecursive{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input a string: ");
        String s = sc.nextLine();
        sc.close();

        if(isPalindrome(s)){
            System.out.println(s + " is a palindrome");
        }else{
            System.out.println(s + " is not a palindrome");
        }
    }

    /*
    a string is a palindrome if its first and last characters are the same and
    what is left between them is a palindrome too, so i compare the two ends and
    then call the method again on the middle.
    eg. racecar -> r = r, check aceca -> a = a, check cec -> c = c, check e -> true
    this makes at most n / 2 calls. the comparison is case sensitive and counts
    spaces, so "Madam" is not a palindrome here.
    */
    public static boolean isPalindrome(String s){
        if(s.length() <= 1) return true; // base case, nothing left to compare

        if(s.charAt(0) != s.charAt(s.length() - 1)) return false;

        return isPalindrome(s.substring(1, s.length() - 1)); // drop both ends
    }
}
