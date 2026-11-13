import java.util.*;

public class DotProduct{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input the length of the vectors: ");
        int len = sc.nextInt();

        int[] a = new int[len];
        int[] b = new int[len];

        System.out.print("Please input the first vector: ");
        for(int i = 0; i < len; i++){
            a[i] = sc.nextInt();
        }
        System.out.print("Please input the second vector: ");
        for(int i = 0; i < len; i++){
            b[i] = sc.nextInt();
        }

        // multiplies matching positions and adds them up
        int dot = 0;
        for(int i = 0; i < len; i++){
            dot = dot + a[i] * b[i];
        }

        System.out.println("Dot product: " + dot);

        sc.close();
    }
}
