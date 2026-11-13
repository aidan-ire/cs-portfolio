/**
 * A road vehicle. Subclasses override getWheels and getType, and can add their own details.
 */
public class Vehicle{
    private String make;
    private String model;
    private int year;

    /**
     * @param make  the manufacturer
     * @param model the model name
     * @param year  the year it was made
     */
    public Vehicle(String make, String model, int year){
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }

    public int getYear(){
        return year;
    }

    public int getWheels(){
        return 4;
    }

    public String getType(){
        return "Vehicle";
    }

    /**
     * @param currentYear the year to measure from
     * @return how many years old the vehicle is
     */
    public int getAge(int currentYear){
        return currentYear - year;
    }

    /*
    describe() is only written once, here. it calls getType() and getWheels(),
    so each subclass's own versions are used without describe() being overridden
    */
    public String describe(){
        return year + " " + make + " " + model + " (" + getType() + ", " + getWheels() + " wheels)";
    }
}
