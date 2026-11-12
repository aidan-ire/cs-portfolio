import java.util.*;

public class Lab8_Tuesday {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = in.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        recQuickSort(arr, 0, n - 1);

        System.out.println("Sorted array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        in.close();
    }

    /*
    quicksort with median-of-three partitioning.
    a pivot is picked and the range is partitioned so everything smaller than the
    pivot is on its left and everything bigger is on its right. the pivot is then
    in its final position, and the two sides are sorted the same way.
    with the rightmost element as the pivot, an array that is already sorted gives
    the worst case, one side is always empty so it takes n levels of recursion and
    O(n^2) time. the array here can have 100000 elements so instead i take the
    median of the first, middle and last elements as the pivot, which splits a
    sorted array exactly in half.
    median-of-three needs at least 4 elements so ranges of 3 or fewer are sorted
    by hand in manualSort.
    this is O(n log n) on average, each level of partitioning is O(n) and there
    are about log2(n) levels when the splits are even.
    */
    public static void recQuickSort(int[] array, int left, int right) {
        int size = right - left + 1;

        if(size <= 3){
            manualSort(array, left, right); // too small to partition
        }else{
            int median = medianOf3(array, left, right);
            int partition = partitionIt(array, left, right, median);
            recQuickSort(array, left, partition - 1);  // sort the left side
            recQuickSort(array, partition + 1, right); // sort the right side
        }
    }

    /*
     * medianOf3
     * sorts the first, middle and last elements of the range into order, then
     * moves the middle one (the median) to position right - 1 to be the pivot.
     * Arguments:
     *   array - the array being sorted
     *   left  - the first index of the range
     *   right - the last index of the range
     * Returns:
     *   the pivot value
     */
    public static int medianOf3(int[] array, int left, int right){
        int center = (left + right) / 2;

        if(array[left] > array[center]) swap(array, left, center);
        if(array[left] > array[right]) swap(array, left, right);
        if(array[center] > array[right]) swap(array, center, right);

        swap(array, center, right - 1); // park the pivot beside the right end
        return array[right - 1];
    }

    /*
     * partitionIt
     * splits the range around the pivot using two scans. the left scan moves
     * right until it finds a value that is not smaller than the pivot, the right
     * scan moves left until it finds one that is not bigger, and the two are
     * swapped. this repeats until the scans cross.
     * Arguments:
     *   array - the array being sorted
     *   left  - the first index of the range
     *   right - the last index of the range
     *   pivot - the pivot value, which is sitting at right - 1
     * Returns:
     *   the index the pivot ends up at
     */
    public static int partitionIt(int[] array, int left, int right, int pivot){
        int leftPtr = left;       // array[left] is already <= pivot from medianOf3
        int rightPtr = right - 1; // array[right] is already >= pivot, and right - 1 is the pivot itself

        while(true){
            while(array[++leftPtr] < pivot){}  // stops at the pivot at the latest
            while(array[--rightPtr] > pivot){} // stops at array[left] at the latest

            if(leftPtr >= rightPtr) break; // scans have crossed, partition done
            else swap(array, leftPtr, rightPtr);
        }

        swap(array, leftPtr, right - 1); // put the pivot into its final position
        return leftPtr;
    }

    /*
     * manualSort
     * sorts a range of 1, 2 or 3 elements with direct comparisons.
     * Arguments:
     *   array - the array being sorted
     *   left  - the first index of the range
     *   right - the last index of the range
     */
    public static void manualSort(int[] array, int left, int right){
        int size = right - left + 1;

        if(size <= 1) return; // nothing to sort
        if(size == 2){
            if(array[left] > array[right]) swap(array, left, right);
            return;
        }

        // size is 3
        if(array[left] > array[right - 1]) swap(array, left, right - 1);
        if(array[left] > array[right]) swap(array, left, right);
        if(array[right - 1] > array[right]) swap(array, right - 1, right);
    }

    // swaps the values at positions a and b
    public static void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
