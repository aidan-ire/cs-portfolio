import java.util.*;

public class SubMirror{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word: ");
        String word = sc.next();

        // each start of the word is printed next to its mirror image
        for(int i = 1; i <= word.length(); i++){
            String sub = word.substring(0, i);
            System.out.println(sub + "|" + reverse(sub));
        }

        sc.close();
    }

    public static String reverse(String str){
        String rev = "";
        for(int i = str.length() - 1; i >= 0; i--){
            rev = rev + str.charAt(i);
        }
        return rev;
    }
}
