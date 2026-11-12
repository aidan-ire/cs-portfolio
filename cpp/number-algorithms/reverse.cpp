#include <iostream>
using namespace std;

/*
builds up the reverse num by continously taking the last digit through the modulus 
operation adding it to the revNum then shaving off that digit from the original
by division and repeating until finished. the negative operation works the same way.
*/

int reverseNums(int x){
    int ans = 0;
    if(x > 0){
        while(x > 0){
            ans = ans * 10 + x % 10; // uses modulus to extract the last digit of x and adds it to revNum after multiplied by 10
            x = x / 10; // strips the last digit
        }
    }else{
        while(x < 0){
            ans = ans * 10 + x % 10; // """"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""
            x = x / 10; //''''''''''''''''''''''''''''''''''''''''''''''
        }
    }
    return ans;
}

int main(){
    int num;
    cout << "Please type a number: "<<endl;
    cin >> num;
    cout << "Reverse of " << num << " is: " << reverseNums(num)<<endl;

    return 0;
}