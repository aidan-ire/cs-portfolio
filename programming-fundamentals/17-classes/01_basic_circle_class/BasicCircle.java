/**
 * A circle described by its radius. The radius can never be negative.
 */
public class BasicCircle{
    private double radius;

    /**
     * Makes a circle with the given radius.
     * @param radius the radius, must be 0 or more
     * @throws IllegalArgumentException if radius is negative
     */
    public BasicCircle(double radius){
        if(radius < 0) throw new IllegalArgumentException("radius can't be negative");
        this.radius = radius;
    }

    /** Makes a circle with radius 1. */
    public BasicCircle(){
        this(1);
    }

    public double getRadius(){
        return radius;
    }

    /**
     * Changes the radius.
     * @param radius the new radius, must be 0 or more
     * @throws IllegalArgumentException if radius is negative, the old radius is kept
     */
    public void setRadius(double radius){
        if(radius < 0) throw new IllegalArgumentException("radius can't be negative");
        this.radius = radius;
    }

    public double getDiameter(){
        return 2 * radius;
    }

    public double getArea(){
        return Math.PI * radius * radius;
    }

    public double getCircumference(){
        return 2 * Math.PI * radius;
    }

    public String toString(){
        return String.format("Circle with radius %.2f", radius);
    }
}
