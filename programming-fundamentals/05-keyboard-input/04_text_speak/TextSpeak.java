import java.util.*;

public class TextSpeak{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine().trim();

        String result = "";
        String[] words = sentence.split("\\s+");
        for(int i = 0; i < words.length; i++){
            result = result + toTextSpeak(words[i]) + " ";
        }

        System.out.println(result.trim());

        sc.close();
    }

    // swaps a whole word for its text-speak form, anything else is returned unchanged
    public static String toTextSpeak(String word){
        String w = word.toLowerCase();

        if(w.equals("you")) return "u";
        if(w.equals("your")) return "ur";
        if(w.equals("are")) return "r";
        if(w.equals("for")) return "4";
        if(w.equals("to") || w.equals("too")) return "2";
        if(w.equals("be")) return "b";
        if(w.equals("see")) return "c";
        if(w.equals("why")) return "y";
        if(w.equals("and")) return "n";
        if(w.equals("before")) return "b4";
        if(w.equals("great")) return "gr8";
        if(w.equals("later")) return "l8r";

        return word;
    }
}
