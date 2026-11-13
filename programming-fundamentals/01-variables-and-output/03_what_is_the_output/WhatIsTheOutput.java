public class WhatIsTheOutput{
    public static void main(String[] args){
        int a = 7;
        int b = 2;

        System.out.println(a / b);              // integer division drops the remainder
        System.out.println(a % b);              // the remainder that was dropped
        System.out.println(a / (double) b);     // casting one side gives a decimal result
        System.out.println(1 + 2 + "3");        // adds the numbers first, then joins the text
        System.out.println("1" + 2 + 3);        // joins left to right, so nothing is added
        System.out.println('a' + 1);            // a char used in arithmetic becomes its int value
        System.out.println((char)('a' + 1));    // casting back gives the next letter
    }
}
