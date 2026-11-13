public class StringArray{
    public static void main(String[] args){
        String[] fruits = {"apple", "banana", "cherry", "date", "elderberry"};

        for(int i = 0; i < fruits.length; i++){
            System.out.println(i + ": " + fruits[i]);
        }

        String longest = fruits[0];
        for(int i = 1; i < fruits.length; i++){
            if(fruits[i].length() > longest.length()) longest = fruits[i];
        }
        System.out.println("Longest: " + longest);

        System.out.println("In reverse:");
        for(int i = fruits.length - 1; i >= 0; i--){
            System.out.println(fruits[i]);
        }
    }
}
