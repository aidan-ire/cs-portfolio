/**
 * A dog with a name, a breed and an age in years.
 */
public class Dog{
    private String name;
    private String breed;
    private int age;

    /**
     * @param name  the dog's name
     * @param breed the dog's breed
     * @param age   the dog's age in years, must be 0 or more
     * @throws IllegalArgumentException if age is negative
     */
    public Dog(String name, String breed, int age){
        if(age < 0) throw new IllegalArgumentException("age can't be negative");
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public String getBreed(){
        return breed;
    }

    public int getAge(){
        return age;
    }

    /** Adds one year to the dog's age. */
    public void haveBirthday(){
        age++;
    }

    public String bark(){
        return name + " says Woof!";
    }

    /*
    a common rule of thumb: the first dog year is about 15 human years, the second about 9,
    and every year after that about 5
    */
    public int humanYears(){
        if(age == 0) return 0;
        if(age == 1) return 15;
        return 24 + (age - 2) * 5;
    }

    public String toString(){
        return name + " the " + breed + ", " + age + (age == 1 ? " year" : " years") + " old";
    }
}
