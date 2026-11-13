import java.util.*;

public class ReverseString2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine().trim();

        System.out.println("Words reversed: " + reverseWords(sentence));

        sc.close();
    }

    /*
    reverses the order of the words, not the letters, eg. "one two three" -> "three two one".
    split(" +") splits on one or more spaces so extra spaces between words don't make empty words
    */
    public static String reverseWords(String sentence){
        if(sentence.length() == 0) return "";

        String[] words = sentence.split(" +");
        String res = "";
        for(int i = words.length - 1; i >= 0; i--){
            res = res + words[i];
            if(i > 0) res = res + " ";
        }

        return res;
    }
}
