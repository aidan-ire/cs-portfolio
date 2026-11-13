public class SortCustomers{
    public static void main(String[] args){
        Customer[] customers = {
            new Customer("John", "Smith", 30),
            new Customer("Alice", "Brown", 25),
            new Customer("Bob", "Adams", 28),
            new Customer("Diana", "Clark", 35),
            new Customer("Diana", "Davis", 22),
            new Customer("Adam", "Smith", 51),
            new Customer("Mary", "Walsh", 18),
            new Customer("Adam", "Reed", 32),
            new Customer("Carl", "English", 29),
            new Customer("Bob", "Gates", 20)
        };

        // each sort works on its own copy so they all start from the original order
        Customer[] byName = customers.clone();
        sortByName(byName);
        System.out.println("Sorted by first name:");
        displayAll(byName);

        Customer[] byAge = customers.clone();
        sortByAge(byAge);
        System.out.println();
        System.out.println("Sorted by age:");
        displayAll(byAge);

        Customer[] byBoth = customers.clone();
        sortByNameThenSurname(byBoth);
        System.out.println();
        System.out.println("Sorted by first name, then surname:");
        displayAll(byBoth);
    }

    /*
    all three sorts are insertion sort, the only thing that changes is how two
    customers are compared. the whole Customer object is moved, not just the
    field being compared, so the name, surname and age stay together.
    insertion sort is stable (it only shifts elements that are strictly bigger
    than the key), so customers with the same first name or age stay in the order
    they were in before.
    */

    // sorts by first name, ascending
    public static void sortByName(Customer[] arr){
        for(int i = 1; i < arr.length; i++){
            Customer key = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j].name.compareTo(key.name) > 0){
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // sorts by age, ascending
    public static void sortByAge(Customer[] arr){
        for(int i = 1; i < arr.length; i++){
            Customer key = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j].age > key.age){
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // sorts by first name, and by surname where the first names are the same
    public static void sortByNameThenSurname(Customer[] arr){
        for(int i = 1; i < arr.length; i++){
            Customer key = arr[i];
            int j = i - 1;

            while(j >= 0 && comesAfter(arr[j], key)){
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // true if a should be placed after b, going by first name and then surname
    public static boolean comesAfter(Customer a, Customer b){
        int res = a.name.compareTo(b.name);

        if(res != 0) return res > 0; // first names differ so they decide it

        return a.surname.compareTo(b.surname) > 0; // same first name, the surname decides
    }

    public static void displayAll(Customer[] arr){
        for(int i = 0; i < arr.length; i++) arr[i].display();
    }
}
