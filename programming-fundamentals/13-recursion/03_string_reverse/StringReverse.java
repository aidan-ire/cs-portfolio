import java.util.*;

public class StringReverse{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a string: ");
        String str = sc.nextLine();

        System.out.println("Reversed: " + reverse(str));

        sc.close();
    }

    /*
    the reverse of a string is the reverse of everything after the first character,
    followed by the first character.
    eg. reverse("abc") = reverse("bc") + "a" = (reverse("c") + "b") + "a" = "cba"
    */
    public static String reverse(String str){
        if(str.length() <= 1) return str;      // base case, empty or one character

        return reverse(str.substring(1)) + str.charAt(0);
    }
}
