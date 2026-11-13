public class TestVehicles{
    public static void main(String[] args){
        Vehicle[] fleet = {
            new Car("Toyota", "Corolla", 2019, 5),
            new Motorbike("Honda", "CB500F", 2022),
            new Truck("Volvo", "FH16", 2015, 3, 26),
            new Vehicle("Generic", "Cart", 2010)
        };

        int totalWheels = 0;
        for(int i = 0; i < fleet.length; i++){
            System.out.println(fleet[i].describe() + ", " + fleet[i].getAge(2026) + " years old");
            totalWheels = totalWheels + fleet[i].getWheels();
        }

        System.out.println("Vehicles: " + fleet.length + ", wheels altogether: " + totalWheels);
    }
}
