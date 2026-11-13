import java.util.*;

public class StringAcceptance{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word of 5-10 letters: ");
        String word = sc.nextLine().trim();

        // keeps asking until the rule is met
        while(!isAcceptable(word)){
            System.out.print("Not accepted, it must be 5-10 letters only. Try again: ");
            word = sc.nextLine().trim();
        }

        System.out.println("Accepted: " + word);

        sc.close();
    }

    public static boolean isAcceptable(String word){
        if(word.length() < 5 || word.length() > 10) return false;

        for(int i = 0; i < word.length(); i++){
            if(!Character.isLetter(word.charAt(i))) return false;
        }

        return true;
    }
}
