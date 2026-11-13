public class TestAnimals{
    public static void main(String[] args){
        // new Animal("x") wouldn't compile, Animal is abstract
        Animal[] farm = {new Dog("Shep"), new Cat("Tom"), new Cow("Daisy"), new Hen("Henrietta")};

        int legs = 0;
        for(int i = 0; i < farm.length; i++){
            System.out.println(farm[i].speak());
            legs = legs + farm[i].legs();

            if(farm[i] instanceof Dog){
                System.out.println("  " + ((Dog) farm[i]).fetch());
            }
        }

        System.out.println("Legs on the farm: " + legs);
    }
}
