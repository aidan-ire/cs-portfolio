import java.util.*;

public class Primes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a limit: ");
        int limit = sc.nextInt();

        String result = "";
        int count = 0;
        for(int i = 2; i <= limit; i++){
            if(isPrime(i)){
                result = result + i + " ";
                count++;
            }
        }

        if(count == 0){
            System.out.println("There are no primes up to " + limit);
        }else if(count == 1){
            System.out.println("Primes up to " + limit + ": " + result.trim());
            System.out.println("1 prime found");
        }else{
            System.out.println("Primes up to " + limit + ": " + result.trim());
            System.out.println(count + " primes found");
        }

        sc.close();
    }

    // a composite number always has a divisor up to its square root,
    // so only i with i * i <= num need checking
    public static boolean isPrime(int num){
        if(num < 2) return false;

        for(int i = 2; i * i <= num; i++){
            if(num % i == 0) return false;
        }

        return true;
    }
}
