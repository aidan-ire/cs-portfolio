import java.util.*;

public class ArrayValueSwap{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int[] nums = {10, 20, 30, 40, 50};

        System.out.print("Please input two positions (0 to " + (nums.length - 1) + "): ");
        int i = sc.nextInt();
        int j = sc.nextInt();

        if(i < 0 || i >= nums.length || j < 0 || j >= nums.length){
            System.out.println("Position out of range");
        }else{
            System.out.print("Before:");
            printArray(nums);

            int temp = nums[i];     // holds one value so it is not overwritten
            nums[i] = nums[j];
            nums[j] = temp;

            System.out.print("After:");
            printArray(nums);
        }

        sc.close();
    }

    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }
}
