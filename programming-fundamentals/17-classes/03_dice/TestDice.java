public class TestDice{
    public static void main(String[] args){
        Dice d1 = new Dice();
        Dice d2 = new Dice();

        System.out.println("Rolling two dice 5 times:");
        for(int i = 1; i <= 5; i++){
            d1.roll();
            d2.roll();
            String note = d1.getValue() == d2.getValue() ? "  doubles!" : "";
            System.out.println("  " + d1.getValue() + " + " + d2.getValue() + " = " + (d1.getValue() + d2.getValue()) + note);
        }

        // each face should come up about 1/6 of the time
        int rolls = 60000;
        int[] counts = new int[7];
        for(int i = 0; i < rolls; i++){
            counts[d1.roll()]++;
        }

        System.out.println("Rolling one die " + rolls + " times:");
        for(int face = 1; face <= 6; face++){
            System.out.printf("  %d: %d (%.1f%%)%n", face, counts[face], 100.0 * counts[face] / rolls);
        }

        Dice d20 = new Dice(20);
        System.out.println("A 20-sided die rolled: " + d20.roll());
    }
}
