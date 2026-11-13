import java.util.*;

public class Anagram{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the first word or phrase: ");
        String a = sc.nextLine();
        System.out.print("Please input the second word or phrase: ");
        String b = sc.nextLine();

        if(isAnagram(a, b)){
            System.out.println("\"" + a + "\" and \"" + b + "\" are anagrams");
        }else{
            System.out.println("\"" + a + "\" and \"" + b + "\" are not anagrams");
        }

        sc.close();
    }

    /*
    two strings are anagrams if they have exactly the same letters. sorting both lists of
    letters puts them in the same order, so after sorting they only need comparing one by one.
    spaces, punctuation and case are ignored
    */
    public static boolean isAnagram(String a, String b){
        char[] x = sortedLetters(a);
        char[] y = sortedLetters(b);

        if(x.length != y.length) return false;
        for(int i = 0; i < x.length; i++){
            if(x[i] != y[i]) return false;
        }

        return true;
    }

    public static char[] sortedLetters(String str){
        String letters = "";
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(Character.isLetter(c)) letters = letters + Character.toLowerCase(c);
        }

        char[] arr = letters.toCharArray();

        // insertion sort, each letter is moved left until the one before it is not bigger
        for(int i = 1; i < arr.length; i++){
            char key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        return arr;
    }
}
