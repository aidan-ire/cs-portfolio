import java.util.*;

public class SortIntegers{
    public static long bubbleSwaps = 0;
    public static long selectionSwaps = 0;

    public static void main(String[] args){
        int n = 1000;
        Random rand = new Random(210); // fixed seed so the counts are the same on every run

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = rand.nextInt(10000);

        // each algorithm sorts its own copy so they all start from the same array
        int[] a = arr.clone();
        int[] b = arr.clone();
        int[] c = arr.clone();

        bubbleSort(a);
        insertionSort(b);
        selectionSort(c);

        System.out.println("n = " + n);
        System.out.println("Bubble sort:    sorted = " + isSorted(a) + ", swaps = " + bubbleSwaps);
        System.out.println("Insertion sort: sorted = " + isSorted(b) + ", no swaps");
        System.out.println("Selection sort: sorted = " + isSorted(c) + ", swaps = " + selectionSwaps);
    }

    /*
    bubble sort compares each pair of neighbours and swaps them if they are in
    the wrong order. after the first pass the biggest element has bubbled up to
    the end, after the second pass the second biggest is in place and so on, so
    each pass can stop one element earlier.
    every swap fixes exactly one pair that was out of order, so the number of
    swaps is the number of out of order pairs in the array.
    this is O(n^2), n - 1 passes of up to n - 1 comparisons.
    */
    public static void bubbleSort(int[] arr){
        int n = arr.length;

        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - 1 - i; j++){ // the last i elements are already in place
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    bubbleSwaps++;
                }
            }
        }
    }

    /*
    insertion sort keeps the left part of the array sorted. it takes the next
    element (key), shifts everything bigger than it one place to the right and
    drops the key into the gap. elements are shifted, not swapped.
    this is O(n^2) in the worst case but close to O(n) if the array is nearly
    sorted, as the inner loop stops straight away.
    */
    public static void insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j]; // shift right to make room
                j--;
            }

            arr[j + 1] = key;
        }
    }

    /*
    selection sort finds the smallest element in the unsorted part and swaps it
    into the first unsorted position, then does the same for the rest.
    there is at most one swap per pass, so n - 1 swaps at most no matter how
    mixed up the array is.
    this is O(n^2), the comparisons are always n(n - 1) / 2.
    */
    public static void selectionSort(int[] arr){
        int n = arr.length;

        for(int i = 0; i < n - 1; i++){
            int min = i; // index of the smallest element found so far

            for(int j = i + 1; j < n; j++){
                if(arr[j] < arr[min]) min = j;
            }

            if(min != i){ // already in the right place, nothing to swap
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                selectionSwaps++;
            }
        }
    }

    public static boolean isSorted(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i + 1]) return false;
        }

        return true;
    }
}
