public class TestBasicSquare{
    public static void main(String[] args){
        BasicSquare a = new BasicSquare(4);
        BasicSquare b = new BasicSquare(2.5);

        printSquare(a);
        printSquare(b);
        System.out.println("First square bigger than the second: " + a.isBiggerThan(b));

        System.out.println("Doubling the second square's side");
        b.setSide(b.getSide() * 2);
        printSquare(b);
        System.out.println("First square bigger than the second: " + a.isBiggerThan(b));

        try{
            new BasicSquare(-1);
        }catch(IllegalArgumentException e){
            System.out.println("new BasicSquare(-1) rejected: " + e.getMessage());
        }
    }

    public static void printSquare(BasicSquare s){
        System.out.println(s);
        System.out.printf("  area %.2f, perimeter %.2f, diagonal %.2f%n", s.getArea(), s.getPerimeter(), s.getDiagonal());
    }
}
