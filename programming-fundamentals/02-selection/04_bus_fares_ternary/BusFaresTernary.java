import java.util.*;

public class BusFaresTernary{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input an age: ");
        int age = sc.nextInt();

        // under 5 and 66+ travel free, under 18 pay the child fare, everyone else the adult fare
        double fare = age < 5 ? 0.0 : age < 18 ? 1.50 : age < 66 ? 3.00 : 0.0;

        System.out.printf("Fare: %.2f euro%n", fare);

        sc.close();
    }
}
