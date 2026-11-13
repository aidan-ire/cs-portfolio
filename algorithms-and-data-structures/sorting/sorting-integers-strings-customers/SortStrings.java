public class SortStrings{
    public static void main(String[] args){
        String[] words = {"pear", "apple", "fig", "banana", "cherry", "apple", "date", "Kiwi"};

        System.out.println("Before:");
        printArray(words);

        insertionSort(words);

        System.out.println("After:");
        printArray(words);
    }

    /*
    insertion sort changed to work on strings. the algorithm is the same as the
    integer one, the only difference is the comparison. strings can't be compared
    with > so compareTo is used, which gives a positive number when the first
    string comes after the second one in dictionary order.
    compareTo goes by character codes so every capital letter comes before every
    lowercase one (Kiwi before apple). compareToIgnoreCase would ignore that.
    */
    public static void insertionSort(String[] arr){
        for(int i = 1; i < arr.length; i++){
            String key = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j].compareTo(key) > 0){ // arr[j] comes after key
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    public static void printArray(String[] arr){
        for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }
}
