public class TestDog{
    public static void main(String[] args){
        Dog rex = new Dog("Rex", "Labrador", 1);
        Dog bella = new Dog("Bella", "Border Collie", 7);

        System.out.println(rex);
        System.out.println(rex.bark());
        System.out.println("In human years: " + rex.humanYears());

        rex.haveBirthday();
        System.out.println("After a birthday: " + rex + ", " + rex.humanYears() + " in human years");

        System.out.println(bella);
        System.out.println(bella.bark());
        System.out.println("In human years: " + bella.humanYears());

        if(rex.getAge() > bella.getAge()){
            System.out.println(rex.getName() + " is older");
        }else if(rex.getAge() < bella.getAge()){
            System.out.println(bella.getName() + " is older");
        }else{
            System.out.println("They are the same age");
        }
    }
}
