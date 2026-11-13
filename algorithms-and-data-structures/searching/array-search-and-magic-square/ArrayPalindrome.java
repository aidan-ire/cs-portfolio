public class ArrayPalindrome{
    public static void main(String[] args){
        int[] a = {1, 2, 3, 2, 1};
        int[] b = {1, 2, 3, 1, 2};
        int[] c = {4, 7, 7, 4}; // even length, no middle element
        int[] d = {9};
        int[] e = {};

        check(a);
        check(b);
        check(c);
        check(d);
        check(e);

        // a string is an array of characters so the same check works on it
        String[] words = {"radar", "madam", "array"};
        for(int i = 0; i < words.length; i++){
            if(isPalindrome(words[i].toCharArray())){
                System.out.println(words[i] + " is palindrome");
            }else{
                System.out.println(words[i] + " is not palindrome");
            }
        }
    }

    // prints the array followed by the result
    public static void check(int[] x){
        for(int i = 0; i < x.length; i++) System.out.print(x[i] + " ");

        if(isPalindrome(x)){
            System.out.println("is palindrome");
        }else{
            System.out.println("is not palindrome");
        }
    }

    /*
    an array is a palindrome if it is the same as its reverse. i don't build the
    reversed array, i just compare the element at the front (i) with the one at
    the back (j) and move both towards the middle. if any pair is different it
    isn't a palindrome, and if i and j meet without finding one then it is.
    eg. 1 2 3 2 1 -> 1 = 1, 2 = 2, then i and j meet at the 3 -> palindrome
    this is O(n), at most n / 2 comparisons.
    */
    public static boolean isPalindrome(int[] x){
        int i = 0;
        int j = x.length - 1;

        while(i < j){
            if(x[i] != x[j]) return false;
            i++;
            j--;
        }

        return true; // also covers empty and single element arrays
    }

    // same idea for an array of characters
    public static boolean isPalindrome(char[] x){
        int i = 0;
        int j = x.length - 1;

        while(i < j){
            if(x[i] != x[j]) return false;
            i++;
            j--;
        }

        return true;
    }
}
