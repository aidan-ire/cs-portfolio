import java.util.*;

public class FindNegatives{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input how many numbers there are: ");
        int len = sc.nextInt();

        int[] nums = new int[len];
        System.out.print("Please input the numbers: ");
        for(int i = 0; i < len; i++){
            nums[i] = sc.nextInt();
        }

        int count = 0;
        String found = "";
        for(int i = 0; i < len; i++){
            if(nums[i] < 0){
                count++;
                found = found + nums[i] + " ";
            }
        }

        if(count == 0){
            System.out.println("No negative numbers");
        }else{
            System.out.println("Negative numbers: " + found.trim());
            System.out.println("Count: " + count);
        }

        sc.close();
    }
}
