import java.util.*;

public class Palindromes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word or phrase: ");
        String str = sc.nextLine();

        // keeps only the letters, in lower case, so spaces and punctuation are ignored
        String clean = "";
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(Character.isLetter(c)) clean = clean + Character.toLowerCase(c);
        }

        boolean isPalindrome = true;
        for(int i = 0; i < clean.length() / 2; i++){
            if(clean.charAt(i) != clean.charAt(clean.length() - 1 - i)){
                isPalindrome = false;
                break;
            }
        }

        if(isPalindrome){
            System.out.println("It is a palindrome");
        }else{
            System.out.println("It is not a palindrome");
        }

        sc.close();
    }
}
