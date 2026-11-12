#include <iostream>
using namespace std;

/*
this program uses euclid's algorithm to find the GCD of two numbers.
From what i gathered the idea is that we divide the first number by the second
and take the remainder, then  we use the second number and the
remainder as our new two numbers to repeat the process.
so when the remainder becomes 0, the other number is the GCD.

The reason this works is that taking the remainder does not change
the common factors shared by the two numbers.
*/

int gcd(int a, int b){
    if(a<0) a = -a;
    if(b<0) b = -b;
    if(a==0&&b==0) return -1; // base case for two zeros, since gcd(0,0) is undefined i return -1
    if(b==0){                 // base case for euclid's algorithm
        return a;
    }

    return gcd(b, a%b); // recursive call that uses the new subtracted values 
}

int main(){
    int a,b;
    cout<<"Please input two numbers :"<<endl;
    cin>>a>>b;
    cout<<"The gcd between "<<a<<" and "<<b<< " is "<<gcd(a,b)<<endl;

    return 0;
}