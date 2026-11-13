import java.util.*;

public class BackwardsSentence{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine().trim();

        String[] words = sentence.split("\\s+");     // any run of spaces separates words

        String result = "";
        for(int i = words.length - 1; i >= 0; i--){
            result = result + words[i] + " ";
        }

        System.out.println(result.trim());

        sc.close();
    }
}
