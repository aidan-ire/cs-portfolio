/**
 * A square is a rectangle whose width and height are the same, so it only needs one side
 * and gets its area and perimeter from Rectangle.
 */
public class Square extends Rectangle{

    /**
     * @param side the length of each side, must be 0 or more
     * @throws IllegalArgumentException if side is negative
     */
    public Square(double side){
        super(side, side);     // the Rectangle constructor sets up both sides and checks them
    }

    public double getSide(){
        return getWidth();
    }

    // only a square has a simple diagonal, side * sqrt(2)
    public double getDiagonal(){
        return getSide() * Math.sqrt(2);
    }

    @Override
    public String toString(){
        return String.format("Square with side %.1f", getSide());
    }
}
