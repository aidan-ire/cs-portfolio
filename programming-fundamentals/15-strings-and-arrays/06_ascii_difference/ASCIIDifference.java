import java.util.*;

public class ASCIIDifference{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word: ");
        String word = sc.next();

        if(word.length() < 2){
            System.out.println("The word needs at least two characters");
            sc.close();
            return;
        }

        int[] diffs = differences(word);
        for(int i = 0; i < diffs.length; i++){
            char a = word.charAt(i);
            char b = word.charAt(i + 1);
            System.out.println(a + " (" + (int) a + ") -> " + b + " (" + (int) b + "): " + diffs[i]);
        }

        int largest = 0;
        for(int i = 1; i < diffs.length; i++){
            if(Math.abs(diffs[i]) > Math.abs(diffs[largest])) largest = i;
        }
        System.out.println("Largest jump: " + word.charAt(largest) + " to " + word.charAt(largest + 1));

        sc.close();
    }

    // the difference between each character and the next one, so a word of n characters gives n - 1.
    // a char subtracted from a char is an int, which is its ASCII value difference
    public static int[] differences(String word){
        int[] res = new int[word.length() - 1];

        for(int i = 0; i < res.length; i++){
            res[i] = word.charAt(i + 1) - word.charAt(i);
        }

        return res;
    }
}
