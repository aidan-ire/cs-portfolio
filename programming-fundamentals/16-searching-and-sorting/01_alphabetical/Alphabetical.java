import java.util.*;

public class Alphabetical{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input some words on one line: ");
        String[] words = sc.nextLine().trim().split(" +");

        sortWords(words);

        System.out.print("In alphabetical order:");
        for(int i = 0; i < words.length; i++){
            System.out.print(" " + words[i]);
        }
        System.out.println();

        sc.close();
    }

    /*
    selection sort: each pass finds the alphabetically first word in the unsorted part
    and swaps it to the front of that part. compareToIgnoreCase is used so "apple" and "Banana"
    are ordered by letter and not by the uppercase letters' lower ASCII values.
    O(n^2) comparisons, but at most n - 1 swaps
    */
    public static void sortWords(String[] words){
        for(int i = 0; i < words.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < words.length; j++){
                if(words[j].compareToIgnoreCase(words[min]) < 0) min = j;
            }

            String temp = words[i];
            words[i] = words[min];
            words[min] = temp;
        }
    }
}
