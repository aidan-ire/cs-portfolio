public class DriversLicenseCategories{
    public static void main(String[] args){
        char license = 'a';

        if(license == 'a'){
            System.out.println("Motorcycle");
        }else if(license == 'b'){
            System.out.println("Car");
        }else if(license == 'c'){
            System.out.println("Truck");
        }else if(license == 'd'){
            System.out.println("Bus");
        }else{
            System.out.println("Invalid license");   // covers any other value
        }
    }
}
