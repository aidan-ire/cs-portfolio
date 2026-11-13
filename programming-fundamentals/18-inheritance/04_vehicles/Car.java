public class Car extends Vehicle{
    private int doors;

    public Car(String make, String model, int year, int doors){
        super(make, model, year);
        this.doors = doors;
    }

    public int getDoors(){
        return doors;
    }

    @Override
    public String getType(){
        return "Car";
    }

    @Override
    public String describe(){
        return super.describe() + ", " + doors + " doors";
    }
}
