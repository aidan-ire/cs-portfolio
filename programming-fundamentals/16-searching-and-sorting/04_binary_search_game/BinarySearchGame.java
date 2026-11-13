import java.util.*;

public class BinarySearchGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Think of a whole number from 1 to 100 and I will guess it.");
        System.out.println("After each guess answer h (higher), l (lower) or c (correct).");

        int low = 1;
        int high = 100;
        int guesses = 0;

        /*
        always guessing the middle of the numbers still possible halves them each time,
        so it takes at most 7 guesses for 100 numbers (2^7 = 128)
        */
        while(true){
            if(low > high){
                System.out.println("Those answers don't add up, there is no number left that fits them.");
                break;
            }

            int guess = (low + high) / 2;
            guesses++;
            System.out.print("Guess " + guesses + ": is it " + guess + "? ");
            String answer = sc.next().toLowerCase();

            while(!answer.equals("h") && !answer.equals("l") && !answer.equals("c")){
                System.out.print("Please answer h, l or c: ");
                answer = sc.next().toLowerCase();
            }

            if(answer.equals("c")){
                System.out.println("Got it in " + guesses + (guesses == 1 ? " guess!" : " guesses!"));
                break;
            }else if(answer.equals("h")){
                low = guess + 1;
            }else{
                high = guess - 1;
            }
        }

        sc.close();
    }
}
