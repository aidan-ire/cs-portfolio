import java.util.*;

public class StackPalindrome{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input a string: ");
        String s = sc.nextLine();
        sc.close();

        System.out.println("Reversed: " + reverse(s));

        if(isPalindrome(s)){
            System.out.println(s + " is a palindrome");
        }else{
            System.out.println(s + " is not a palindrome");
        }
    }

    /*
    a stack is last in, first out, so if i push the characters of the string in
    order and then pop them all off again they come out backwards.
    eg. push c, a, t -> the stack has t on top -> pop t, a, c -> "tac"
    this is O(n), each character is pushed once and popped once.
    */
    public static String reverse(String s){
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            stack.push(s.charAt(i));
        }

        String rev = "";
        while(!stack.isEmpty()){
            rev = rev + stack.pop();
        }

        return rev;
    }

    // a string is a palindrome if the reversed string is equal to the original
    public static boolean isPalindrome(String s){
        return s.equals(reverse(s)); // case sensitive, and spaces count
    }
}
