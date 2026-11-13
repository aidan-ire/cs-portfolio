import java.util.*;

public class PokerHand{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Cards are a rank (2-10, J, Q, K, A) and a suit (H, D, C, S), eg. 10H or QS");
        System.out.print("Please input 5 cards: ");
        String[] cards = sc.nextLine().trim().toUpperCase().split(" +");

        String problem = checkHand(cards);
        if(!problem.equals("")){
            System.out.println("Invalid hand: " + problem);
        }else{
            System.out.println(classify(cards));
        }

        sc.close();
    }

    // returns 2-14 for a card's rank (J = 11, Q = 12, K = 13, A = 14), or -1 if it isn't valid
    public static int rankOf(String card){
        String r = card.substring(0, card.length() - 1);
        if(r.equals("J")) return 11;
        if(r.equals("Q")) return 12;
        if(r.equals("K")) return 13;
        if(r.equals("A")) return 14;
        if(r.equals("10")) return 10;
        if(r.length() == 1 && r.charAt(0) >= '2' && r.charAt(0) <= '9') return r.charAt(0) - '0';
        return -1;
    }

    public static char suitOf(String card){
        return card.charAt(card.length() - 1);
    }

    // returns what is wrong with the hand, or an empty string if it is 5 different valid cards
    public static String checkHand(String[] cards){
        if(cards.length != 5) return "a hand is exactly 5 cards, " + cards.length + " were given";

        for(int i = 0; i < 5; i++){
            String card = cards[i];
            if(card.length() < 2 || card.length() > 3 || rankOf(card) == -1) return card + " is not a card";

            char s = suitOf(card);
            if(s != 'H' && s != 'D' && s != 'C' && s != 'S') return card + " is not a card";

            for(int j = 0; j < i; j++){
                if(cards[j].equals(card)) return card + " appears twice";
            }
        }

        return "";
    }

    /*
    counts how many cards there are of each rank. the pattern of counts gives most hands:
    a 4 is four of a kind, a 3 and a 2 is a full house, two 2s is two pair and so on.
    a straight is 5 different ranks in a row, and A 2 3 4 5 also counts with the ace low.
    the checks go from the best hand down so the first match is the answer
    */
    public static String classify(String[] cards){
        int[] counts = new int[15];        // index 2-14, one per rank
        boolean flush = true;
        for(int i = 0; i < 5; i++){
            counts[rankOf(cards[i])]++;
            if(suitOf(cards[i]) != suitOf(cards[0])) flush = false;
        }

        int pairs = 0;
        boolean three = false;
        boolean four = false;
        for(int r = 2; r <= 14; r++){
            if(counts[r] == 2) pairs++;
            if(counts[r] == 3) three = true;
            if(counts[r] == 4) four = true;
        }

        boolean straight = false;
        for(int low = 2; low <= 10; low++){
            if(counts[low] == 1 && counts[low + 1] == 1 && counts[low + 2] == 1 && counts[low + 3] == 1 && counts[low + 4] == 1) straight = true;
        }
        if(counts[14] == 1 && counts[2] == 1 && counts[3] == 1 && counts[4] == 1 && counts[5] == 1) straight = true;     // ace low

        if(straight && flush) return "Straight flush";
        if(four) return "Four of a kind";
        if(three && pairs == 1) return "Full house";
        if(flush) return "Flush";
        if(straight) return "Straight";
        if(three) return "Three of a kind";
        if(pairs == 2) return "Two pair";
        if(pairs == 1) return "One pair";
        return "High card";
    }
}
