public class MethodOverloading1{
    public static void main(String[] args){
        System.out.println("max(3, 9) = " + max(3, 9));
        System.out.println("max(2.5, 1.75) = " + max(2.5, 1.75));
        System.out.println("max(4, 11, 7) = " + max(4, 11, 7));
        System.out.println("max(\"apple\", \"pear\") = " + max("apple", "pear"));
    }

    // java picks which max to call from the number and types of the arguments
    public static int max(int a, int b){
        if(a > b) return a;
        return b;
    }

    public static double max(double a, double b){
        if(a > b) return a;
        return b;
    }

    // reuses the two int version rather than repeating the comparison
    public static int max(int a, int b, int c){
        return max(max(a, b), c);
    }

    // the larger string is the one that comes later alphabetically
    public static String max(String a, String b){
        if(a.compareTo(b) > 0) return a;
        return b;
    }
}
