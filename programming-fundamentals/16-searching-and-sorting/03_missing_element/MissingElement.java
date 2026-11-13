import java.util.*;

public class MissingElement{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input n: ");
        int n = sc.nextInt();
        if(n < 2){
            System.out.println("n must be at least 2");
            sc.close();
            return;
        }

        int[] arr = new int[n - 1];
        System.out.print("Please input the numbers 1 to " + n + " in any order, leaving one out: ");
        for(int i = 0; i < n - 1; i++){
            arr[i] = sc.nextInt();
        }

        insertionSort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
        System.out.println("Missing number: " + findMissing(arr));

        sc.close();
    }

    public static void insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /*
    once sorted, every number before the gap sits at index value - 1 and every number after
    it sits one place early. so the answer is the first index where arr[i] != i + 1, and a binary
    search finds it in O(log n): if arr[mid] is in its place the gap is to the right, otherwise
    it is at mid or to the left. if nothing is out of place the missing number is n itself.
    eg. [1, 2, 4, 5] -> index 2 holds 4, not 3, so 3 is missing
    */
    public static int findMissing(int[] arr){
        int low = 0;
        int high = arr.length;      // high = length covers the case where n is missing

        while(low < high){
            int mid = (low + high) / 2;
            if(arr[mid] == mid + 1){
                low = mid + 1;
            }else{
                high = mid;
            }
        }

        return low + 1;
    }
}
