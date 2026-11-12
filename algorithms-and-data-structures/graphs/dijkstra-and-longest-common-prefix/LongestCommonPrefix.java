import java.util.Scanner;


public class LongestCommonPrefix {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter words separated by spaces:");
        String inputLine = scanner.nextLine();

        // Split the line into an array of words
        String[] words = inputLine.trim().split("\\s+");

        String result = findLongestCommonPrefix(words);

        System.out.println("Longest Common Prefix: " + result);

    }

    /*
    starts by assuming the whole first word is the common prefix, then checks it
    against every other word and cuts it back to the part they share. the prefix
    can only ever get shorter, and if it reaches length 0 there is nothing in
    common so it stops early.
    eg. machine marvellous maybe -> "machine" vs "marvellous" leaves "ma",
    "ma" vs "maybe" leaves "ma"
    this is O(n * m) for n words where m is the length of the first word, as each
    word is compared one character at a time against the prefix.
    */
    public static String findLongestCommonPrefix(String[] array) {
        if(array == null || array.length == 0) return ""; // no words at all

        String prefix = array[0];

        for(int i = 1; i < array.length; i++){
            int j = 0;
            // count how many characters at the start match
            while(j < prefix.length() && j < array[i].length() && prefix.charAt(j) == array[i].charAt(j)){
                j++;
            }
            prefix = prefix.substring(0, j); // keep only the part that matched

            if(prefix.length() == 0) return ""; // nothing in common, no need to check the rest
        }

        return prefix;
    }

}
