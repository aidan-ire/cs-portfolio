public class ReverseBits{

    public static void main (String args[]) {

        int num1 = 43261596;
        int num2 = 2147483644;

        int reverse1 = reverseBits(num1);
        int reverse2 = reverseBits(num2);

        System.out.println(reverse1);
        System.out.println(reverse2);
    }

    /*
    reverses the 32 bits of n by taking the bits off the right hand end of n one
    at a time and pushing them onto the right hand end of the result. each time
    the result is shifted left to make room, so the first bit taken (the lowest
    bit of n) ends up as the highest bit of the result after 32 shifts.
    eg. with 4 bits, 0011 -> take 1, 1, 0, 0 -> 1100
    the loop always runs 32 times so this is O(1).
    */
    public static int reverseBits(int n) {
        int res = 0;

        for(int i = 0; i < 32; i++){
            res = res << 1;      // make room for the next bit
            res = res | (n & 1); // n & 1 is the lowest bit of n
            n = n >>> 1;         // drop that bit, >>> so 0s are shifted in even if n is negative
        }

        return res;
    }
}
