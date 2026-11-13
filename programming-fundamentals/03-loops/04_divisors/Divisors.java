import java.util.*;

public class Divisors{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a positive number: ");
        int num = sc.nextInt();

        if(num < 1){
            System.out.println("The number must be positive");
        }else{
            String result = "";
            for(int i = 1; i <= num; i++){
                if(num % i == 0) result = result + i + " ";
            }
            System.out.println("Divisors of " + num + ": " + result.trim());
        }

        sc.close();
    }
}
