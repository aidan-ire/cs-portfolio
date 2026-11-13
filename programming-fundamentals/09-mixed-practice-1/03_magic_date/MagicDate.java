import java.util.*;

public class MagicDate{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a day, month and year: ");
        int day = sc.nextInt();
        int month = sc.nextInt();
        int year = sc.nextInt();

        if(day < 1 || day > 31 || month < 1 || month > 12){
            System.out.println("Invalid date");
        }else if(day * month == year % 100){    // a magic date has day times month equal to the last two digits of the year
            System.out.println(day + "/" + month + "/" + year + " is a magic date");
        }else{
            System.out.println(day + "/" + month + "/" + year + " is not a magic date");
        }

        sc.close();
    }
}
