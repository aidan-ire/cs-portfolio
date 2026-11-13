import java.util.*;

public class Password{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please choose a password: ");
        String pw = sc.nextLine();

        String problems = checkPassword(pw);
        while(!problems.equals("")){
            System.out.println("Password rejected, it needs:" + problems);
            System.out.print("Please choose a password: ");
            pw = sc.nextLine();
            problems = checkPassword(pw);
        }

        System.out.println("Password accepted");

        sc.close();
    }

    /*
    a password needs at least 8 characters, an uppercase letter, a lowercase letter, a digit,
    and no spaces. every rule is checked so the user sees all the problems at once,
    each one on its own line. an empty string means the password passed
    */
    public static String checkPassword(String pw){
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpace = false;

        for(int i = 0; i < pw.length(); i++){
            char c = pw.charAt(i);
            if(Character.isUpperCase(c)) hasUpper = true;
            else if(Character.isLowerCase(c)) hasLower = true;
            else if(Character.isDigit(c)) hasDigit = true;
            else if(c == ' ') hasSpace = true;
        }

        String problems = "";
        if(pw.length() < 8) problems = problems + "\n  - at least 8 characters";
        if(!hasUpper) problems = problems + "\n  - an uppercase letter";
        if(!hasLower) problems = problems + "\n  - a lowercase letter";
        if(!hasDigit) problems = problems + "\n  - a digit";
        if(hasSpace) problems = problems + "\n  - no spaces";

        return problems;
    }
}
