#include <iostream>
using namespace std;

/*
this is O(log n) as we perform integer division on y which results in hitting the base case,
as integer division will always reach 0 this results in roughly log2(y) calls until our answer is reached
eg. power(2,8) -> power(2,4) -> power(2,2) -> power(2,1) -> power(2,0)
*/

int power(int x, int y){
    if(y == 0) return 1; //base case, any base to the power of 0 is 1

    int half = power(x, y/2); // this step is what makes it O(log n) as it recursively calculates half the power

    if(y % 2 == 0) return half * half; // if even, returns (x^(y/2))^2
    else return x * half * half; // else returns x*(x^(y/2))^2
}

int main(){
    int x,y;
    cout<<"Please enter base and exponent respectively :"<<endl;
    cin>>x>>y;
    cout<<x<<" to the power of "<<y<<" is "<<power(x,y)<<endl;
}