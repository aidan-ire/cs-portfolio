import java.util.*;

public class ValidName{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input your full name: ");
        String name = sc.nextLine().trim();

        while(!isValidName(name)){
            System.out.println("Invalid name. Use 2-30 letters, with single spaces, hyphens or apostrophes between them.");
            System.out.print("Please input your full name: ");
            name = sc.nextLine().trim();
        }

        System.out.println("Hello, " + name);

        sc.close();
    }

    /*
    a valid name is 2-30 characters, starts and ends with a letter, and only has letters,
    spaces, hyphens and apostrophes. a space, hyphen or apostrophe can't be next to another one,
    so "Mary-Kate O'Brien" is fine but "Mary--Kate" and "Sean  Kelly" are not
    */
    public static boolean isValidName(String name){
        if(name.length() < 2 || name.length() > 30) return false;
        if(!Character.isLetter(name.charAt(0))) return false;
        if(!Character.isLetter(name.charAt(name.length() - 1))) return false;

        for(int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            if(Character.isLetter(c)) continue;

            if(c != ' ' && c != '-' && c != '\'') return false;
            if(!Character.isLetter(name.charAt(i + 1))) return false;    // safe, the last char is a letter
        }

        return true;
    }
}
