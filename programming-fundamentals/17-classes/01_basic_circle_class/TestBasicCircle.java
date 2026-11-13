public class TestBasicCircle{
    public static void main(String[] args){
        BasicCircle unit = new BasicCircle();
        BasicCircle c = new BasicCircle(3);

        printCircle(unit);
        printCircle(c);

        System.out.println("Changing the radius of the second circle to 0.5");
        c.setRadius(0.5);
        printCircle(c);

        // a negative radius is rejected and the circle keeps its old radius
        try{
            c.setRadius(-2);
        }catch(IllegalArgumentException e){
            System.out.println("setRadius(-2) rejected: " + e.getMessage());
        }
        printCircle(c);
    }

    public static void printCircle(BasicCircle c){
        System.out.println(c);
        System.out.printf("  diameter %.2f, area %.2f, circumference %.2f%n", c.getDiameter(), c.getArea(), c.getCircumference());
    }
}
