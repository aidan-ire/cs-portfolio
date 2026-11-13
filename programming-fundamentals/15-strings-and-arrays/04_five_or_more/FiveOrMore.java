import java.util.*;

public class FiveOrMore{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Please input numbers, ending with 0:");

        int count = 0;
        int total = 0;
        int num = sc.nextInt();
        while(num != 0){       // 0 only marks the end, so it is never counted
            total++;
            if(num >= 5) count++;
            num = sc.nextInt();
        }

        System.out.println(count + " of the " + total + " numbers entered are 5 or more");

        sc.close();
    }
}
