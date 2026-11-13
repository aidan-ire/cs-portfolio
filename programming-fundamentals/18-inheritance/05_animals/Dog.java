public class Dog extends Animal{

    public Dog(String name){
        super(name);
    }

    @Override
    public String sound(){
        return "Woof";
    }

    public String fetch(){
        return getName() + " fetches the ball";
    }
}
