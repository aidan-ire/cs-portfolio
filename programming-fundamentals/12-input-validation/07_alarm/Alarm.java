import java.util.*;

public class Alarm{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the current time (hh:mm): ");
        String time = sc.nextLine().trim();
        while(toMinutes(time) == -1){
            System.out.print("Invalid time, use 24-hour hh:mm, eg. 07:45. Try again: ");
            time = sc.nextLine().trim();
        }

        System.out.print("Please input the minutes until the alarm: ");
        String line = sc.nextLine().trim();
        while(!isWholeNumber(line)){
            System.out.print("That is not a whole number of minutes. Try again: ");
            line = sc.nextLine().trim();
        }

        int total = toMinutes(time) + Integer.parseInt(line);
        int days = total / (24 * 60);
        String alarm = toTime(total % (24 * 60));      // wraps past midnight

        if(days == 0){
            System.out.println("The alarm goes off at " + alarm);
        }else if(days == 1){
            System.out.println("The alarm goes off at " + alarm + " the next day");
        }else{
            System.out.println("The alarm goes off at " + alarm + ", " + days + " days later");
        }

        sc.close();
    }

    // turns "hh:mm" into minutes after midnight, or -1 if it isn't a valid 24-hour time
    public static int toMinutes(String time){
        if(time.length() != 5 || time.charAt(2) != ':') return -1;

        String hh = time.substring(0, 2);
        String mm = time.substring(3);
        if(!isWholeNumber(hh) || !isWholeNumber(mm)) return -1;

        int h = Integer.parseInt(hh);
        int m = Integer.parseInt(mm);
        if(h > 23 || m > 59) return -1;

        return h * 60 + m;
    }

    // pads with a 0 so 7:05 prints as 07:05
    public static String toTime(int minutes){
        int h = minutes / 60;
        int m = minutes % 60;
        return (h < 10 ? "0" : "") + h + ":" + (m < 10 ? "0" : "") + m;
    }

    // up to 6 digits, so parseInt can't overflow
    public static boolean isWholeNumber(String s){
        if(s.length() == 0 || s.length() > 6) return false;

        for(int i = 0; i < s.length(); i++){
            if(!Character.isDigit(s.charAt(i))) return false;
        }

        return true;
    }
}
