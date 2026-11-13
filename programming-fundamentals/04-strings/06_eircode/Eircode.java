import java.util.*;

public class Eircode{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input an Eircode: ");
        String code = sc.nextLine().trim().toUpperCase();

        if(isEircode(code)){
            System.out.println("Valid Eircode format");
        }else{
            System.out.println("Invalid Eircode format");
        }

        sc.close();
    }

    // checks the shape only: routing key (letter and two digits, or D6W) then four letters or digits
    public static boolean isEircode(String code){
        if(code.length() == 8 && code.charAt(3) == ' '){
            code = code.substring(0, 3) + code.substring(4);    // drops the optional space
        }
        if(code.length() != 7) return false;

        String key = code.substring(0, 3);
        boolean validKey = key.equals("D6W") || (isLetter(key.charAt(0)) && isDigit(key.charAt(1)) && isDigit(key.charAt(2)));
        if(!validKey) return false;

        for(int i = 3; i < 7; i++){
            if(!isLetter(code.charAt(i)) && !isDigit(code.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isLetter(char c){
        return c >= 'A' && c <= 'Z';
    }

    public static boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }
}
