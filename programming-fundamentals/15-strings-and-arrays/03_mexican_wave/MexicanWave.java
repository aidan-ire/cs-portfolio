import java.util.*;

public class MexicanWave{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word or phrase: ");
        String str = sc.nextLine().toLowerCase();

        String[] wave = mexicanWave(str);
        for(int i = 0; i < wave.length; i++){
            System.out.println(wave[i]);
        }

        sc.close();
    }

    /*
    one version of the string per letter, with just that letter in upper case.
    spaces and other non-letters can't be capitalised so they are skipped,
    which means the array is sized by counting the letters first
    */
    public static String[] mexicanWave(String str){
        int letters = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isLetter(str.charAt(i))) letters++;
        }

        String[] res = new String[letters];
        int j = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(!Character.isLetter(c)) continue;

            res[j] = str.substring(0, i) + Character.toUpperCase(c) + str.substring(i + 1);
            j++;
        }

        return res;
    }
}
