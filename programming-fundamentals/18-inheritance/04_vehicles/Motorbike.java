public class Motorbike extends Vehicle{

    public Motorbike(String make, String model, int year){
        super(make, model, year);
    }

    @Override
    public int getWheels(){
        return 2;
    }

    @Override
    public String getType(){
        return "Motorbike";
    }
}
