import java.util.*;

public class ValidAge{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int age = -1;
        while(age == -1){
            System.out.print("Please input your age: ");
            String line = sc.nextLine().trim();
            age = parseAge(line);

            if(age == -1) System.out.println("\"" + line + "\" is not a valid age, it must be a whole number from 0 to 120");
        }

        System.out.println("Age accepted: " + age);

        sc.close();
    }

    // returns the age, or -1 if line isn't made only of digits or is outside 0-120.
    // checking the digits first means Integer.parseInt can never throw here
    public static int parseAge(String line){
        if(line.length() == 0 || line.length() > 3) return -1;

        for(int i = 0; i < line.length(); i++){
            if(!Character.isDigit(line.charAt(i))) return -1;
        }

        int age = Integer.parseInt(line);
        if(age > 120) return -1;

        return age;
    }
}
