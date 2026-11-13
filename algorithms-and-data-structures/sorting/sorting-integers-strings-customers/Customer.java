class Customer {
    String name;
    String surname;
    int age;

    Customer(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    void display() {
        System.out.println(name + " " + surname + " (" + age + ")");
    }
}
