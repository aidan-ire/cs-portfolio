public class MethodOverloading2{
    public static void main(String[] args){
        System.out.printf("Circle, radius 2.5:          %.2f%n", area(2.5));
        System.out.printf("Rectangle, 4 by 6.5:         %.2f%n", area(4, 6.5));
        System.out.printf("Triangle, sides 3, 4 and 5:  %.2f%n", area(3, 4, 5));
        System.out.printf("Triangle, sides 1, 2 and 10: %.2f%n", area(1, 2, 10));
    }

    // one argument is treated as a circle's radius
    public static double area(double radius){
        return Math.PI * radius * radius;
    }

    // two arguments are a rectangle's width and height
    public static double area(double width, double height){
        return width * height;
    }

    /*
    three arguments are a triangle's sides, using Heron's formula:
    s is half the perimeter and the area is sqrt(s(s-a)(s-b)(s-c)).
    if one side is longer than the other two put together there is no triangle, so i return 0
    */
    public static double area(double a, double b, double c){
        if(a + b <= c || a + c <= b || b + c <= a) return 0;

        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
