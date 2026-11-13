import java.util.*;

public class AddAs{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a sentence: ");
        String sentence = sc.nextLine();

        int count = countAs(sentence);
        if(count == 1){
            System.out.println("There is 1 'a' in the sentence");
        }else{
            System.out.println("There are " + count + " 'a's in the sentence");
        }

        sc.close();
    }

    public static int countAs(String str){
        int count = 0;

        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == 'a' || c == 'A') count++;
        }

        return count;
    }
}
