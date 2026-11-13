/**
 * A rectangle with a fixed width and height.
 */
public class Rectangle{
    private double width;
    private double height;

    /**
     * @param width  the width, must be 0 or more
     * @param height the height, must be 0 or more
     * @throws IllegalArgumentException if either side is negative
     */
    public Rectangle(double width, double height){
        if(width < 0 || height < 0) throw new IllegalArgumentException("sides can't be negative");
        this.width = width;
        this.height = height;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getArea(){
        return width * height;
    }

    public double getPerimeter(){
        return 2 * (width + height);
    }

    public String toString(){
        return String.format("Rectangle %.1f x %.1f", width, height);
    }
}
