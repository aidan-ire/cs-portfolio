import java.util.*;

public class VariousArrayTasks{
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

        System.out.println("Sum: " + sum(arr));
        System.out.printf("Average: %.2f%n", average(arr));
        System.out.println("Max: " + max(arr));
        System.out.println("Min: " + min(arr));
        System.out.println("Even numbers: " + countEvens(arr));

        sc.close();
    }

    public static int sum(int[] arr){
        int total = 0;
        for(int i = 0; i < arr.length; i++){
            total = total + arr[i];
        }
        return total;
    }

    // casts before dividing so the decimal part isn't lost
    public static double average(int[] arr){
        return (double) sum(arr) / arr.length;
    }

    public static int max(int[] arr){
        int res = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > res) res = arr[i];
        }
        return res;
    }

    public static int min(int[] arr){
        int res = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] < res) res = arr[i];
        }
        return res;
    }

    // % 2 is 0 for negative even numbers too, eg. -4 % 2 == 0
    public static int countEvens(int[] arr){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % 2 == 0) count++;
        }
        return count;
    }
}
