import java.util.*;

public class StraightFlush{
    public static String[] suits = {"H","D","C","S"};
    public static String[] ranks = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};

    public static void main(String[] args){
        Random rand = new Random();

        ArrayList<String> deck = new ArrayList<>();
        for(String suit: suits) {
            for(String rank: ranks) {
                deck.add(rank + suit);
            }
        }

        // 10 possible lowest cards (A to 10) in each of 4 suits = 40 hands out of C(52,5) = 2,598,960
        double exact = 40.0 / 2598960;

        System.out.println("Probability of a straight flush in 5 cards");
        System.out.printf("Exact: %.8f%n", exact);
        System.out.println();
        System.out.println("N\t\tsuccesses\testimate");

        // the hand is very rare so small values of N would mostly give 0
        for(int N = 100000; N <= 10000000; N *= 10){
            int success = 0;

            for(int i = 0; i < N; i++){
                Collections.shuffle(deck, rand);
                if(isStraightFlush(deck)) success++; // the hand is the first 5 cards of the shuffled deck
            }

            System.out.printf("%-10d\t%d\t\t%.8f%n", N, success, (double)success / N);
        }
    }

    /*
    checks if the first 5 cards of the deck are a straight flush, which is five
    cards of the same suit with ranks in a row. a card is the rank followed by
    one character for the suit (eg. "10H", "KS").
    i mark which of the 13 ranks are in the hand. if a suit doesn't match the
    first card it can't be a flush so i stop there. as all five cards are the
    same suit the ranks are all different, so it is a straight if five marks in a
    row are set. the ace is high in the array, so A 2 3 4 5 is checked on its own.
    a royal flush (10 J Q K A) is counted as well, it is the highest straight flush.
    */
    public static boolean isStraightFlush(ArrayList<String> deck){
        boolean[] has = new boolean[13];
        char suit = ' ';

        for(int i = 0; i < 5; i++){
            String card = deck.get(i);
            char s = card.charAt(card.length() - 1);             // suit is the last character
            String rank = card.substring(0, card.length() - 1);  // everything before it, "10" is two characters

            if(i == 0) suit = s;
            if(s != suit) return false; // not a flush

            has[rankIndex(rank)] = true;
        }

        // the ace low straight, A 2 3 4 5
        if(has[12] && has[0] && has[1] && has[2] && has[3]) return true;

        // any other straight starts at 2 (index 0) up to 10 (index 8)
        for(int low = 0; low <= 8; low++){
            if(has[low] && has[low + 1] && has[low + 2] && has[low + 3] && has[low + 4]) return true;
        }

        return false;
    }

    // position of a rank in the ranks array, 0 for "2" up to 12 for "A"
    public static int rankIndex(String rank){
        for(int i = 0; i < ranks.length; i++){
            if(ranks[i].equals(rank)) return i;
        }

        return -1;
    }
}
