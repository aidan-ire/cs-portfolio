import java.util.*;

/**
 * A single die with a chosen number of sides, numbered 1 to sides.
 */
public class Dice{
    private int sides;
    private int value;
    private Random rand = new Random();

    /**
     * Makes a die and rolls it once so it always shows a value.
     * @param sides the number of sides, at least 2
     * @throws IllegalArgumentException if sides is less than 2
     */
    public Dice(int sides){
        if(sides < 2) throw new IllegalArgumentException("a die needs at least 2 sides");
        this.sides = sides;
        this.value = rand.nextInt(sides) + 1;
    }

    /** Makes an ordinary six-sided die. */
    public Dice(){
        this(6);
    }

    /**
     * Rolls the die.
     * @return the new value, from 1 to sides
     */
    public int roll(){
        value = rand.nextInt(sides) + 1;   // nextInt(sides) gives 0 to sides - 1
        return value;
    }

    public int getValue(){
        return value;
    }

    public int getSides(){
        return sides;
    }
}
