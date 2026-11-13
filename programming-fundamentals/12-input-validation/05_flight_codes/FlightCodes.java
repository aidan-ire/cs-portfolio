import java.util.*;

public class FlightCodes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a flight code: ");
        String code = sc.nextLine().trim();

        String problem = checkCode(code);
        while(!problem.equals("")){
            System.out.println("Invalid: " + problem);
            System.out.print("Please input a flight code: ");
            code = sc.nextLine().trim();
            problem = checkCode(code);
        }

        System.out.println(code + " is valid, airline " + code.substring(0, 2) + ", flight number " + code.substring(2));

        sc.close();
    }

    // a flight code is 2 uppercase letters then 3 or 4 digits, eg. EI123 or FR1234.
    // returns what is wrong with code, or an empty string if it is valid
    public static String checkCode(String code){
        if(code.length() < 5 || code.length() > 6) return "it must be 5 or 6 characters long";

        for(int i = 0; i < 2; i++){
            char c = code.charAt(i);
            if(c < 'A' || c > 'Z') return "the first two characters must be uppercase letters";
        }

        for(int i = 2; i < code.length(); i++){
            if(!Character.isDigit(code.charAt(i))) return "the letters must be followed by 3 or 4 digits";
        }

        return "";
    }
}
