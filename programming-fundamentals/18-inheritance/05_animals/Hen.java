public class Hen extends Animal{

    public Hen(String name){
        super(name);
    }

    @Override
    public String sound(){
        return "Cluck";
    }

    @Override
    public int legs(){
        return 2;
    }
}
