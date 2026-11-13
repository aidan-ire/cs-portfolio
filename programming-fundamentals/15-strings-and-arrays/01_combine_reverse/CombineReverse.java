import java.util.*;

public class CombineReverse{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the first string: ");
        String first = sc.nextLine();
        System.out.print("Please input the second string: ");
        String second = sc.nextLine();

        System.out.println("Combined: " + reverse(second) + reverse(first));

        sc.close();
    }

    public static String reverse(String str){
        String rev = "";
        for(int i = str.length() - 1; i >= 0; i--){
            rev = rev + str.charAt(i);
        }
        return rev;
    }
}
