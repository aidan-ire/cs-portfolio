import java.util.*;

public class LinearSearch1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int[] arr = {12, 45, 7, 23, 56, 89, 34, 7, 91, 18};
        System.out.println("Array: " + Arrays.toString(arr));

        System.out.print("Please input a number to search for: ");
        int target = sc.nextInt();

        int index = linearSearch(arr, target);
        if(index == -1){
            System.out.println(target + " is not in the array");
        }else{
            System.out.println(target + " found at index " + index);
        }

        sc.close();
    }

    /*
    checks each element from the start until it finds target, so the first match is returned.
    this is O(n) as in the worst case (target missing or last) every element is checked once
    */
    public static int linearSearch(int[] arr, int target){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == target) return i;
        }

        return -1;      // -1 can never be a real index so it means not found
    }
}
