import java.util.*;

public class WellOrdered{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word: ");
        String word = sc.next().toLowerCase();

        // well ordered means no letter is alphabetically before the letter that came ahead of it
        boolean wellOrdered = true;
        for(int i = 1; i < word.length(); i++){
            if(word.charAt(i) < word.charAt(i - 1)){
                wellOrdered = false;
                break;
            }
        }

        if(wellOrdered){
            System.out.println(word + " is well ordered");
        }else{
            System.out.println(word + " is not well ordered");
        }

        sc.close();
    }
}
