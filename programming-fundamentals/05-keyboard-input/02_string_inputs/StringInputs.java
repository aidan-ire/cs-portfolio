import java.util.*;

public class StringInputs{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input your first name: ");
        String first = sc.nextLine();
        System.out.print("Please input your surname: ");
        String last = sc.nextLine();

        System.out.println("Hello " + first + " " + last);

        sc.close();
    }
}
