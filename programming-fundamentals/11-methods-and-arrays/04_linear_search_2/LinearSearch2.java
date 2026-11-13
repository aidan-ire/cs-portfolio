import java.util.*;

public class LinearSearch2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int[] arr = {4, 8, 15, 4, 16, 23, 4, 42, 8, 15};
        System.out.println("Array: " + Arrays.toString(arr));

        System.out.print("Please input a number to search for: ");
        int target = sc.nextInt();

        int[] positions = findAll(arr, target);
        if(positions.length == 0){
            System.out.println(target + " is not in the array");
        }else if(positions.length == 1){
            System.out.println(target + " appears once, at index " + positions[0]);
        }else{
            String list = "";
            for(int i = 0; i < positions.length; i++){
                list = list + positions[i];
                if(i < positions.length - 1) list = list + ", ";
            }
            System.out.println(target + " appears " + positions.length + " times, at indexes " + list);
        }

        sc.close();
    }

    /*
    a linear search that keeps going after the first match.
    the first pass counts the matches so the result array can be made the right size,
    the second pass fills it in. still O(n) as two passes is 2n steps
    */
    public static int[] findAll(int[] arr, int target){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == target) count++;
        }

        int[] res = new int[count];
        int j = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == target){
                res[j] = i;
                j++;
            }
        }

        return res;
    }
}
