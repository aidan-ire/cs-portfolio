public class TestEmployee{
    public static void main(String[] args){
        Employee[] staff = {
            new Employee("Niamh Byrne", 101, 42000),
            new Employee("Ciaran Walsh", 102, 38500),
            new Employee("Aoife Doyle", 103, 51250)
        };

        System.out.println("Staff:");
        printStaff(staff);

        System.out.println("Giving everyone a 3% raise and Ciaran an extra 5%");
        for(int i = 0; i < staff.length; i++){
            staff[i].giveRaise(3);
        }
        staff[1].giveRaise(5);
        printStaff(staff);

        double total = 0;
        for(int i = 0; i < staff.length; i++){
            total = total + staff[i].getSalary();
        }
        System.out.printf("Total yearly pay: %.2f%n", total);
        System.out.printf("Aoife's monthly pay: %.2f%n", staff[2].getMonthlySalary());
    }

    public static void printStaff(Employee[] staff){
        for(int i = 0; i < staff.length; i++){
            System.out.println("  " + staff[i]);
        }
    }
}
