public class Arithmetic{
    public static void main(String[] args){
        int num1 = 5;
        int num2 = 10;
        int num3 = 15;
        int result1 = 0;
        int result2 = 0;
        double result3 = 0.0;

        result1 = num1 + num2 + num3;
        result2 = num2 * num3;
        result3 = num3 / num1;   // 15 / 5 divides exactly, so int division loses nothing

        System.out.println("Addition: " + result1);
        System.out.println("Multiplication: " + result2);
        System.out.println("Division: " + result3);
    }
}
