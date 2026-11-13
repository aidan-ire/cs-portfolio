public class TestSquares{
    public static void main(String[] args){
        // a Rectangle array can hold Squares too, since every Square is a Rectangle
        Rectangle[] shapes = {
            new Rectangle(3, 5),
            new Square(4),
            new Rectangle(2.5, 2.5),
            new Square(1.5)
        };

        for(int i = 0; i < shapes.length; i++){
            Rectangle r = shapes[i];
            System.out.printf("%s: area %.2f, perimeter %.2f%n", r, r.getArea(), r.getPerimeter());

            // getDiagonal is only in Square, so the reference has to be cast to reach it
            if(r instanceof Square){
                Square s = (Square) r;
                System.out.printf("  it is a Square, diagonal %.2f%n", s.getDiagonal());
            }
        }
    }
}
