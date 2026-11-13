/**
 * A square described by the length of its side. The side can never be negative.
 */
public class BasicSquare{
    private double side;

    /**
     * Makes a square with the given side length.
     * @param side the side length, must be 0 or more
     * @throws IllegalArgumentException if side is negative
     */
    public BasicSquare(double side){
        if(side < 0) throw new IllegalArgumentException("side can't be negative");
        this.side = side;
    }

    public double getSide(){
        return side;
    }

    /**
     * Changes the side length.
     * @param side the new side length, must be 0 or more
     * @throws IllegalArgumentException if side is negative, the old side is kept
     */
    public void setSide(double side){
        if(side < 0) throw new IllegalArgumentException("side can't be negative");
        this.side = side;
    }

    public double getArea(){
        return side * side;
    }

    public double getPerimeter(){
        return 4 * side;
    }

    // by pythagoras the diagonal is sqrt(side^2 + side^2) = side * sqrt(2)
    public double getDiagonal(){
        return side * Math.sqrt(2);
    }

    /**
     * Checks if this square is bigger than another one.
     * @param other the square to compare with
     * @return true if this square's area is larger
     */
    public boolean isBiggerThan(BasicSquare other){
        return getArea() > other.getArea();
    }

    public String toString(){
        return String.format("Square with side %.2f", side);
    }
}
