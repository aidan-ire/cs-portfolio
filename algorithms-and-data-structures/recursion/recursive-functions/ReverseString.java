import java.util.*;

public class ReverseString{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input a string: ");
        String s = sc.nextLine();
        sc.close();

        System.out.println("Reversed: " + reverse(s));
    }

    /*
    to reverse a string i take off the first character, reverse the rest of the
    string (the recursive call) and put the first character on the end.
    eg. reverse("abc") -> reverse("bc") + a -> reverse("c") + b + a -> cba
    this makes n calls, but each one also builds a new string with substring and +
    so it is O(n^2) overall.
    */
    public static String reverse(String s){
        if(s.length() <= 1) return s; // base case, empty or one character is its own reverse

        return reverse(s.substring(1, s.length())) + s.charAt(0);
    }
}
