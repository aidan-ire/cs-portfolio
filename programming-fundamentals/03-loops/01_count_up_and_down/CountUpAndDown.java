import java.util.*;

public class CountUpAndDown{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number: ");
        int n = sc.nextInt();

        for(int i = 1; i <= n; i++){
            System.out.println(i);
        }
        for(int i = n - 1; i >= 1; i--){   // starts at n - 1 so n is not printed twice
            System.out.println(i);
        }

        sc.close();
    }
}
