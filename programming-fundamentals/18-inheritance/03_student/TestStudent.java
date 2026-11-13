public class TestStudent{
    public static void main(String[] args){
        Student s1 = new Student("Ciara", 20, "26100123", 6);
        Student s2 = new Student("Darragh", 19, "26100456", 6);

        int[] m1 = {72, 65, 58, 81};
        for(int i = 0; i < m1.length; i++){
            s1.addMark(m1[i]);
        }

        int[] m2 = {35, 42, 28};
        for(int i = 0; i < m2.length; i++){
            s2.addMark(m2[i]);
        }

        System.out.println("Adding a mark of 105 to Ciara: " + s1.addMark(105));

        Person[] people = {new Person("Tom", 52), s1, s2};
        for(int i = 0; i < people.length; i++){
            System.out.println(people[i].introduce());
            System.out.println("  " + people[i]);
        }

        System.out.println(s1.getName() + " passing: " + s1.isPassing());
        System.out.println(s2.getName() + " passing: " + s2.isPassing());
    }
}
