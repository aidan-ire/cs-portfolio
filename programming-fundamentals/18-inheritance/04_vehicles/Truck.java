public class Truck extends Vehicle{
    private int axles;
    private double maxLoad;

    /**
     * @param axles   the number of axles, each one with 2 wheels on each side
     * @param maxLoad the most it can carry in tonnes
     */
    public Truck(String make, String model, int year, int axles, double maxLoad){
        super(make, model, year);
        this.axles = axles;
        this.maxLoad = maxLoad;
    }

    public double getMaxLoad(){
        return maxLoad;
    }

    // 4 wheels per axle, 2 on each side
    @Override
    public int getWheels(){
        return axles * 4;
    }

    @Override
    public String getType(){
        return "Truck";
    }

    @Override
    public String describe(){
        return super.describe() + ", carries up to " + maxLoad + " tonnes";
    }
}
