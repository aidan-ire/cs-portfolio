import java.util.*;

public class Seasons{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a month number (1-12): ");
        int month = sc.nextInt();

        String season = getSeason(month);
        if(season.equals("")){
            System.out.println(month + " is not a valid month");
        }else{
            System.out.println("Month " + month + " is in " + season);
        }

        sc.close();
    }

    // meteorological seasons, each a block of three whole months starting with winter in December.
    // returns an empty string for a number outside 1-12
    public static String getSeason(int month){
        if(month < 1 || month > 12) return "";

        if(month == 12 || month <= 2) return "Winter";
        if(month <= 5) return "Spring";
        if(month <= 8) return "Summer";
        return "Autumn";
    }
}
