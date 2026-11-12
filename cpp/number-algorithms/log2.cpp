#include <iostream>
using namespace std;

/*
checks if a number is a power of two by continously dividing it by 2.
if at any point the number is odd then it cant be a power of two and then we get all the way down to 1 then it is.
this is O(log n) since x gets halved every loop.
*/

bool isPowerOfTwo(int x){
    if(x <= 0) return false; // 0 and negatives can never be a power of two

    while(x > 1){
        if(x % 2 != 0) return false; // odd number thats not 1 so its not a power of two
        x = x / 2; // continously divide by 2
    }

    return true;
}

int main(){
    int x;
    cout<<"Please input a number: "<<endl;
    cin>>x;
    if(isPowerOfTwo(x)) cout<<x<<" is a power of two"<<endl;
    else cout<<x<<" is NOT a power of two"<<endl;
    cout<<boolalpha<<isPowerOfTwo(x)<<endl;
}