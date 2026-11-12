import java.util.*;

public class palindrome{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter word: ");
        String in = sc.nextLine();
        sc.close();
        
        System.out.println(palindromeCheck(in));
    }

    public static boolean palindromeCheck(String x){
        int i = 0;
        int j = x.length()-1;

        while(i<j){
            if(x.charAt(i) != x.charAt(j)){
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

}