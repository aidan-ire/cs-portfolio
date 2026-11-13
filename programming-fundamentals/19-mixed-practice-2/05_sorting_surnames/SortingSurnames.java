import java.util.*;

public class SortingSurnames{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("How many names? ");
        int n = sc.nextInt();
        sc.nextLine();      // clears the rest of the line after the number

        if(n < 1){
            System.out.println("Please enter at least 1 name");
            sc.close();
            return;
        }

        String[] names = new String[n];
        for(int i = 0; i < n; i++){
            System.out.print("Name " + (i + 1) + ": ");
            names[i] = sc.nextLine().trim();
        }

        sortBySurname(names);

        System.out.println("Sorted by surname:");
        for(int i = 0; i < n; i++){
            System.out.println("  " + names[i]);
        }

        sc.close();
    }

    // the surname is everything after the last space, or the whole name if there is no space
    public static String surname(String name){
        return name.substring(name.lastIndexOf(' ') + 1);
    }

    // negative if a comes before b: by surname first, then by the full name when surnames match
    public static int compareNames(String a, String b){
        int bySurname = surname(a).compareToIgnoreCase(surname(b));
        if(bySurname != 0) return bySurname;
        return a.compareToIgnoreCase(b);
    }

    /*
    insertion sort: each name is moved left past every name that should come after it.
    names with the same surname are then ordered by first name, eg. Anne Kelly before Sean Kelly
    */
    public static void sortBySurname(String[] names){
        for(int i = 1; i < names.length; i++){
            String key = names[i];
            int j = i - 1;
            while(j >= 0 && compareNames(names[j], key) > 0){
                names[j + 1] = names[j];
                j--;
            }
            names[j + 1] = key;
        }
    }
}
