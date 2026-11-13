import java.util.*;

public class CentPincher{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int days = sc.nextInt();

        long saved = 0;
        long today = 1;     // what is put away on the current day, doubles each day
        int day = 1;

        while(day <= days){
            saved = saved + today;
            today = today * 2;
            day++;
        }

        System.out.println(saved);

        sc.close();
    }
}
