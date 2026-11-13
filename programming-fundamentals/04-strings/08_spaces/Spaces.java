import java.util.*;

public class Spaces{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine();

        int count = 0;
        String noSpaces = "";
        for(int i = 0; i < sentence.length(); i++){
            if(sentence.charAt(i) == ' '){
                count++;
            }else{
                noSpaces = noSpaces + sentence.charAt(i);
            }
        }

        System.out.println("Number of spaces: " + count);
        System.out.println("Without spaces: " + noSpaces);

        sc.close();
    }
}
