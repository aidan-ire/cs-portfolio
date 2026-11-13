import java.util.*;

public class PrimeNumberChecker{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number: ");
        int num = sc.nextInt();

        boolean isPrime = num >= 2;     // 0, 1 and negatives are not prime

        // a composite number always has a divisor up to its square root
        for(int i = 2; i * i <= num; i++){
            if(num % i == 0){
                isPrime = false;
                break;
            }
        }

        if(isPrime){
            System.out.println(num + " is prime");
        }else{
            System.out.println(num + " is not prime");
        }

        sc.close();
    }
}
