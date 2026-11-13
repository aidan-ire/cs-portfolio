import java.util.*;

public class FizzBuzz1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a number: ");
        int num = sc.nextInt();

        if(num % 15 == 0){          // checked first, as it is divisible by both 3 and 5
            System.out.println("FizzBuzz");
        }else if(num % 3 == 0){
            System.out.println("Fizz");
        }else if(num % 5 == 0){
            System.out.println("Buzz");
        }else{
            System.out.println(num);
        }

        sc.close();
    }
}
