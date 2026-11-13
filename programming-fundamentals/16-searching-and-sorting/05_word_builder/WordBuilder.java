import java.util.*;

public class WordBuilder{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Type one letter at a time, then \"done\" to finish.");

        String word = "";
        System.out.print("Letter: ");
        String input = sc.next();
        while(!input.equalsIgnoreCase("done")){
            if(input.length() == 1 && Character.isLetter(input.charAt(0))){
                word = word + input;
                System.out.println("Word so far: " + word);
            }else{
                System.out.println("\"" + input + "\" is not a single letter");
            }

            System.out.print("Letter: ");
            input = sc.next();
        }

        if(word.length() == 0){
            System.out.println("No letters were entered");
        }else{
            System.out.println("Your word: " + word);
            System.out.println("Its letters in alphabetical order: " + new String(sortLetters(word)));
        }

        sc.close();
    }

    /*
    bubble sort on the word's letters, comparing in lower case so 'B' goes after 'a'.
    each pass bubbles the biggest remaining letter to the end, and the sort stops early
    once a pass makes no swaps since the letters are already in order
    */
    public static char[] sortLetters(String word){
        char[] arr = word.toCharArray();

        for(int pass = 0; pass < arr.length - 1; pass++){
            boolean swapped = false;
            for(int i = 0; i < arr.length - 1 - pass; i++){
                if(Character.toLowerCase(arr[i]) > Character.toLowerCase(arr[i + 1])){
                    char temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if(!swapped) break;
        }

        return arr;
    }
}
