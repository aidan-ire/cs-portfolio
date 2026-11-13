import java.util.*;

public class BubbleSortIntegers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the number of values: ");
        int n = sc.nextInt();
        if(n < 1){
            System.out.println("The array needs at least one value");
            sc.close();
            return;
        }

        int[] arr = new int[n];
        System.out.print("Please input the values: ");
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Start:  " + Arrays.toString(arr));
        int swaps = bubbleSort(arr);
        System.out.println("Sorted with " + swaps + (swaps == 1 ? " swap" : " swaps"));

        sc.close();
    }

    /*
    compares each neighbouring pair and swaps them if they are the wrong way round,
    so after pass p the largest p values are in their final places at the end.
    the array is printed after every pass, and the sort stops as soon as a pass makes no swaps.
    O(n^2) in the worst case (reversed), O(n) if it is already sorted.
    returns the total number of swaps
    */
    public static int bubbleSort(int[] arr){
        int swaps = 0;

        for(int pass = 1; pass < arr.length; pass++){
            boolean swapped = false;
            for(int i = 0; i < arr.length - pass; i++){
                if(arr[i] > arr[i + 1]){
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.println("Pass " + pass + ": " + Arrays.toString(arr));
            if(!swapped) break;
        }

        return swaps;
    }
}
