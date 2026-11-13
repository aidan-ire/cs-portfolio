/**
 * An animal with a name. Animal is abstract because there is no general sound
 * an "animal" makes: every subclass has to say what its sound is.
 */
public abstract class Animal{
    private String name;

    public Animal(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    /** @return the noise this kind of animal makes */
    public abstract String sound();

    /** @return the number of legs, 4 unless a subclass says otherwise */
    public int legs(){
        return 4;
    }

    public String speak(){
        return name + " the " + getClass().getSimpleName() + " says " + sound();
    }
}
