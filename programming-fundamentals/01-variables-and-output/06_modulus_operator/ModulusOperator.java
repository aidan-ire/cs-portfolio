public class ModulusOperator{
    public static void main(String[] args){
        int num = 1981;

        int ones = num % 10;
        int tens = num / 10 % 10;           // strips the last digit, then takes the next one
        int hundreds = num / 100 % 10;
        int thousands = num / 1000 % 10;

        System.out.println("The digits in the number " + num + " are:");
        System.out.println(thousands);
        System.out.println(hundreds);
        System.out.println(tens);
        System.out.println(ones);
    }
}
