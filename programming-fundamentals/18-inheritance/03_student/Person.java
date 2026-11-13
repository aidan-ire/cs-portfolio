/**
 * A person with a name and an age.
 */
public class Person{
    private String name;
    private int age;

    /**
     * @param name the person's name
     * @param age  their age in years, must be 0 or more
     * @throws IllegalArgumentException if age is negative
     */
    public Person(String name, int age){
        if(age < 0) throw new IllegalArgumentException("age can't be negative");
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String introduce(){
        return "Hi, I'm " + name + " and I'm " + age;
    }

    // overrides the toString every class gets from Object
    @Override
    public String toString(){
        return name + " (" + age + ")";
    }
}
