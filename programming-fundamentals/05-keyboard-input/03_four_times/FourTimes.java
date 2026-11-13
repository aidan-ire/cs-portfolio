import java.util.*;

public class FourTimes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a word: ");
        String word = sc.next();

        String result = "";
        for(int i = 0; i < 4; i++){
            result = result + word + " ";
        }

        System.out.println(result.trim());

        sc.close();
    }
}
