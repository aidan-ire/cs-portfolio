import java.util.*;

public class CompareStrings{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input two words: ");
        String a = sc.next();
        String b = sc.next();

        if(a.equals(b)){
            System.out.println("The words are identical");
        }else if(a.equalsIgnoreCase(b)){
            System.out.println("The words are the same apart from case");
        }else if(a.compareTo(b) < 0){       // compareTo orders by character value, so capitals sort first
            System.out.println(a + " comes before " + b);
        }else{
            System.out.println(b + " comes before " + a);
        }

        sc.close();
    }
}
