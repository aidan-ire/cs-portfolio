import java.util.*;

public class twoSum{
    public static void main(String[] args){
        int[] x = {2,0,7,8,9,1,5,6};
        int target = 9;

        int[] out = twoSum(x, target);

        for(int ans: out){
            System.out.println(ans);
        }
    }

    public static int[] twoSum(int[] x, int target){
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < x.length; i++){
            int complement = target - x[i];
            if(map.containsKey(complement)) return new int[]{map.get(complement), i};
            map.put(x[i],i);
        }
        return null;
    }
}