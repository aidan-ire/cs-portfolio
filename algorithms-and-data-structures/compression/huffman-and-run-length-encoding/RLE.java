import java.util.Scanner;

public class RLE{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string to be compressed: ");
        String input = scanner.nextLine();
        scanner.close();

        String compressed = compress(input);
        System.out.println("Compressed as: " + compressed);

        if(input.length() > 0){ // no ratio for an empty string, it would divide by 0
            double ratio = 100.0 * compressed.length() / input.length();
            System.out.printf("%.1f%%%n", ratio);
        }
    }

    /*
    run-length encoding. i walk along the string counting how many times the
    current character repeats, and when the next character is different (or the
    string ends) the run is finished, so the count is written out followed by the
    character.
    eg. 3322251 -> two 3s, three 2s, one 5, one 1 -> 23 32 15 11 -> 23321511
    this is O(n) as each character is looked at once.
    */
    public static String compress(String x){
        String out = "";
        int count = 1;

        for(int i = 0; i < x.length(); i++){
            // the run ends at the last character or when the next one is different
            if(i == x.length() - 1 || x.charAt(i) != x.charAt(i + 1)){
                out = out + count + x.charAt(i);
                count = 1; // start counting the next run
            }else{
                count++;
            }
        }

        return out;
    }
}
