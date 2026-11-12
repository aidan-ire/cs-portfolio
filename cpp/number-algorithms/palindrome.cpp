#include <iostream>
using namespace std;

/*
uses the same-ish algorithm to reverse the integer as reverse.cpp by continously adding the last digit of x
dividing by 10 to strip the last digit and repeating the process but it performs a check at the end which
returns if the number is truly a palindrome
*/

bool isPalindrome(int x){
    int num = x;
    int revNum = 0;
    if(x<0) return false; // edge case for negative numbers
    while(x>0){
        revNum = revNum * 10 + x % 10;
        x = x / 10;
    }
    return num==revNum; // check here
}

int main(){
    int x;
    cout<<"Please input a number: "<<endl;
    cin>>x;
    if(isPalindrome(x)) cout<<x<<" is a palindrome"<<endl;
    else cout<<x<<" is NOT a palindrome"<<endl;
    cout<<boolalpha<<isPalindrome(x)<<endl;
}