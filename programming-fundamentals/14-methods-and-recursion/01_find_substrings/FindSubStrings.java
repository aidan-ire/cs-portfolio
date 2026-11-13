import java.util.*;

public class FindSubStrings{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a string: ");
        String str = sc.nextLine();
        System.out.print("Please input the substring to find: ");
        String sub = sc.nextLine();

        if(sub.length() == 0 || sub.length() > str.length()){
            System.out.println("\"" + sub + "\" can't be found in \"" + str + "\"");
            sc.close();
            return;
        }

        String positions = "";
        int count = 0;
        for(int i = 0; i <= str.length() - sub.length(); i++){
            if(matchesAt(str, sub, i)){
                positions = positions + i + " ";
                count++;
            }
        }

        if(count == 0){
            System.out.println("\"" + sub + "\" was not found");
        }else if(count == 1){
            System.out.println("\"" + sub + "\" found once, starting at index " + positions.trim());
        }else{
            System.out.println("\"" + sub + "\" found " + count + " times, starting at indexes " + positions.trim().replace(" ", ", "));
        }

        sc.close();
    }

    /*
    checks if sub appears in str starting at index start, comparing one character at a time.
    every start position is tried, so overlapping matches are counted too,
    eg. "aa" is found twice in "aaa", at 0 and 1
    */
    public static boolean matchesAt(String str, String sub, int start){
        for(int j = 0; j < sub.length(); j++){
            if(str.charAt(start + j) != sub.charAt(j)) return false;
        }

        return true;
    }
}
