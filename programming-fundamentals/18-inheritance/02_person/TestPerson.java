public class TestPerson{
    public static void main(String[] args){
        Person p = new Person("Orla", 19);
        Teacher t = new Teacher("Mr Kavanagh", 45, "Computer Science");

        System.out.println(p.introduce());
        System.out.println(t.introduce());

        // the variable's type is Person but the Teacher version of introduce() runs,
        // because java picks the method from the object, not the variable
        Person q = t;
        System.out.println("Through a Person variable: " + q.introduce());

        System.out.println("toString: " + p + " | " + t);
        System.out.println("Teacher's name from Person: " + t.getName());
    }
}
