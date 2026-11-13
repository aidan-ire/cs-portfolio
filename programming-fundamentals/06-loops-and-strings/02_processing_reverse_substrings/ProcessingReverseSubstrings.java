import java.util.*;

public class ProcessingReverseSubstrings{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine();

        String result = "";
        String word = "";
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(c == ' '){
                result = result + word + " ";   // the word is finished, so it goes into the result
                word = "";
            }else{
                word = c + word;                // putting c in front builds the word up reversed
            }
        }
        result = result + word;                 // the last word has no space after it

        System.out.println(result);

        sc.close();
    }
}
