import java.util.*;

public class Heights{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        double[] heights = new double[5];
        System.out.print("Please input five heights in metres: ");
        for(int i = 0; i < heights.length; i++){
            heights[i] = sc.nextDouble();
        }

        double tallest = heights[0];
        double shortest = heights[0];
        double total = 0;
        for(int i = 0; i < heights.length; i++){
            if(heights[i] > tallest) tallest = heights[i];
            if(heights[i] < shortest) shortest = heights[i];
            total = total + heights[i];
        }

        System.out.println("Tallest: " + tallest);
        System.out.println("Shortest: " + shortest);
        System.out.printf("Average: %.2f%n", total / heights.length);

        sc.close();
    }
}
