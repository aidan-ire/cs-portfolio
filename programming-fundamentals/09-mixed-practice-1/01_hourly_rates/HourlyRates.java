import java.util.*;

public class HourlyRates{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the hours worked and the hourly rate: ");
        double hours = sc.nextDouble();
        double rate = sc.nextDouble();

        // the first 40 hours are paid at the normal rate, anything over is paid at time and a half
        double pay;
        if(hours <= 40){
            pay = hours * rate;
        }else{
            pay = 40 * rate + (hours - 40) * rate * 1.5;
        }

        System.out.printf("Pay: %.2f%n", pay);

        sc.close();
    }
}
