import java.util.*;

public class LeapYears{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a year: ");
        int year = sc.nextInt();

        // every 4th year, except century years unless they are divisible by 400
        boolean isLeap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;

        if(isLeap){
            System.out.println(year + " is a leap year");
        }else{
            System.out.println(year + " is not a leap year");
        }

        sc.close();
    }
}
