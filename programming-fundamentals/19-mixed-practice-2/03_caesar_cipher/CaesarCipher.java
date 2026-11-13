import java.util.*;

public class CaesarCipher{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Encrypt or decrypt (e/d): ");
        String mode = sc.nextLine().trim().toLowerCase();
        while(!mode.equals("e") && !mode.equals("d")){
            System.out.print("Please answer e or d: ");
            mode = sc.nextLine().trim().toLowerCase();
        }

        System.out.print("Please input the message: ");
        String message = sc.nextLine();
        System.out.print("Please input the key (shift): ");
        int key = sc.nextInt();

        if(mode.equals("e")){
            System.out.println("Encrypted: " + shift(message, key));
        }else{
            System.out.println("Decrypted: " + shift(message, -key));    // decrypting is shifting back by the key
        }

        sc.close();
    }

    /*
    moves every letter forward by n places in the alphabet, wrapping round from z to a.
    the same method does both directions since a negative n moves letters back.
    n is brought into 0-25 first so huge or negative keys still work, eg. -3 becomes 23.
    case is kept and anything that isn't a letter is left as it is
    */
    public static String shift(String message, int n){
        n = ((n % 26) + 26) % 26;

        String res = "";
        for(int i = 0; i < message.length(); i++){
            char c = message.charAt(i);
            if(c >= 'a' && c <= 'z'){
                c = (char)('a' + (c - 'a' + n) % 26);
            }else if(c >= 'A' && c <= 'Z'){
                c = (char)('A' + (c - 'A' + n) % 26);
            }
            res = res + c;
        }

        return res;
    }
}
