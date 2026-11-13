import java.util.*;

public class LicensePlates{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a license plate (eg. 191-D-12345): ");
        String plate = sc.next().toUpperCase();

        int first = plate.indexOf('-');
        int second = plate.lastIndexOf('-');

        // needs a three digit year code, a county section and a number section
        if(first != 3 || second <= first + 1 || second == plate.length() - 1){
            System.out.println("Invalid license plate");
        }else{
            String year = "20" + plate.substring(0, 2);
            char half = plate.charAt(2);
            String county = plate.substring(first + 1, second);
            String number = plate.substring(second + 1);

            System.out.println("Year: " + year);
            if(half == '1'){
                System.out.println("Registered: January to June");
            }else{
                System.out.println("Registered: July to December");
            }
            System.out.println("County code: " + county);
            System.out.println("Number: " + number);
        }

        sc.close();
    }
}
