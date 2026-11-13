import java.util.*;

public class CleanString{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a string: ");
        String str = sc.nextLine();

        System.out.println("Cleaned: \"" + clean(str) + "\"");

        sc.close();
    }

    /*
    looks at the first character and cleans the rest of the string recursively.
    a letter is kept in lower case, anything else (digits, spaces, punctuation) is dropped.
    eg. clean("Hi 5!") = "h" + clean("i 5!") = "h" + "i" + clean(" 5!") = ... = "hi"
    */
    public static String clean(String str){
        if(str.length() == 0) return "";       // base case

        char c = str.charAt(0);
        String rest = clean(str.substring(1));

        if(Character.isLetter(c)) return Character.toLowerCase(c) + rest;
        return rest;
    }
}
