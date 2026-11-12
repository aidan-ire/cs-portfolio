import java.util.*;
public class Lab7_StringSearch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = in.nextLine();
        System.out.print("Enter pattern: ");
        String pattern = in.nextLine();
        KMPSearch(text, pattern);
        in.close();
    }

    /*
    KMP search. i is the position in the text and j is how many characters of the
    pattern are currently matched. the naive search goes back in the text after a
    mismatch and starts the pattern again from 0, but KMP never moves i backwards.
    when a mismatch happens after j matched characters, the LPS table says how
    long the longest prefix of the pattern is that is also a suffix of what was
    just matched, so those characters are already known to match and j can drop
    straight to that value instead of 0.
    eg. pattern ABABCABAB, matched ABAB then hit a mismatch -> lps[3] is 2, so AB
    is still matched and the comparison carries on from pattern[2].
    this is O(n + m), building the table is O(m) and the search is O(n) since i
    only ever moves forward.
    */
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();

        // an empty pattern or one longer than the text can't be found
        if(m == 0 || m > n){
            System.out.println(-1);
            return;
        }

        int[] lps = computeLPS(pattern);
        boolean found = false;
        int i = 0; // position in the text
        int j = 0; // position in the pattern

        while(i < n){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;

                if(j == m){ // whole pattern matched, it started m characters back
                    System.out.print((i - j) + " ");
                    found = true;
                    j = lps[j - 1]; // keep going so overlapping matches are found too
                }
            }else if(j > 0){
                j = lps[j - 1]; // fall back in the pattern, i stays where it is
            }else{
                i++; // nothing matched at all, move on in the text
            }
        }

        if(!found) System.out.print(-1);
        System.out.println();
    }

    /*
    builds the LPS (longest proper prefix which is also a suffix) table.
    lps[i] is the length of the longest prefix of the pattern that is also a
    suffix of pattern[0..i], not counting the whole thing itself.
    len is the length of the prefix currently being extended. if the next
    character continues it then lps[i] = len + 1, if not then len falls back to
    lps[len - 1] (the next shorter prefix that could still work) and tries again.
    eg. ABABCABAB -> 0 0 1 2 0 1 2 3 4
    */
    public static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m]; // lps[0] is always 0, a single character has no proper prefix
        int len = 0;
        int i = 1;

        while(i < m){
            if(pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }else if(len > 0){
                len = lps[len - 1]; // try the next shorter prefix, i is not moved
            }else{
                lps[i] = 0;
                i++;
            }
        }

        return lps;
    }
}
