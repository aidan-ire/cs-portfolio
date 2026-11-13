import java.util.*;

public class Decode{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the message: ");
        String message = sc.nextLine();
        System.out.print("Please input the shift: ");
        int shift = sc.nextInt();

        shift = ((shift % 26) + 26) % 26;   // keeps the shift between 0 and 25, even if it is negative

        String decoded = "";
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(Character.isLetter(c)){
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                c = (char)((c - base - shift + 26) % 26 + base);    // moves back by the shift, wrapping round the alphabet
            }
            decoded = decoded + c;
        }

        System.out.println("Decoded: " + decoded);

        sc.close();
    }
}
