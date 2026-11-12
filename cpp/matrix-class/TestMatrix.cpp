#include "Matrix.h"
#include <stdexcept>
#include <iostream>
using namespace std;

//build line: g++ -o testMatrix TestMatrix.cpp Matrix.cpp

int passed = 0, failed = 0;

// prints PASS or FAIL for a test and adds to the passed/failed count
void report(const char* test_name, bool check) {
    cout << (check ? "  PASS  " : "  FAIL  ") << test_name << endl;
    if (check) passed++; else failed++;
}

// test case 1 for matrix constructor.
// checks a new matrix is created and has every value set to 0
void Test_Case_1_Constructor(){
    unsigned int i, j;
    bool zeroed = true;
    Matrix test(3,3);
    for(i = 0; i < test.rows(); i++){
        for(j = 0; j < test.columns(); j++){
            if(test.get(i,j) != 0) zeroed = false;
        }
    }
    report("Test_Case_1_Constructor", zeroed);
}

// test case 1 for matrix copy constructor.
// checks the copy has the same shape and values and changing it doesnt change the original
void Test_Case_1_CopyConstructor(){
    Matrix test(2,2);
    test.set(0, 0, 1);

    Matrix copy(test);
    bool shapeMatch = ((test.rows() == copy.rows()) && (test.columns() == copy.columns()) && (test.get(0, 0) == copy.get(0, 0)));
    copy.set(0, 0, 0);

    report("Test_Case_1_CopyConstructor", shapeMatch && (test.get(0, 0) == 1));
}

// test case 1 for matrix function rows().
// checks rows() returns the right number of rows
void Test_Case_1_Rows(){
    Matrix test(6,64);
    report("Test_Case_1_Rows", test.rows() == 6);

}

// test case 1 for matrix function columns().
// checks columns() returns the right number of columns
void Test_Case_1_Columns(){
    Matrix test(6,64);
    report("Test_Case_1_Columns", test.columns() == 64);
}

// test case 1 for matrix function get().
// checks get() returns the value that was set at (i,j)
void Test_Case_1_Get(){
    int val = 8;

    Matrix test(6, 12);
    test.set(5, 5, val);
    int res = test.get(5, 5);

    report("Test_Case_1_Get", val == res);
}

// test case 2 for matrix function get().
// checks get() throws out_of_range when i or j are out of bounds
void Test_Case_2_Get(){
    bool rowThrow = false, colThrow = false;
    Matrix test(6, 12);

    try{
        test.get(6,1);
    }catch(const out_of_range&){
        rowThrow = true;
    }

    try{
        test.get(5,12);
    }catch(const out_of_range&){
        colThrow = true;
    }

    report("Test_Case_2_Get", rowThrow && colThrow);
}

// test case 1 for matrix function set().
// checks set() only changes the value at (i,j) and leaves the rest at 0
void Test_Case_1_Set(){
    int val = 8;

    Matrix test(3, 3);
    test.set(2, 2, val);
    int res = test.get(2, 2);
    bool pass = true;
    
    for(unsigned int i = 0; i < test.rows(); i++){
        for(unsigned int j = 0; j < test.columns(); j++){
            if(test.get(i,j) != 0 && !(i == 2 && j == 2)) pass = false;
        }
    }

    report("Test_Case_1_Set", (res == val) && pass);
}

// test case 2 for matrix function set().
// checks set() throws out_of_range when i or j are out of bounds
void Test_Case_2_Set(){
    bool rowThrow = false, colThrow = false;
    Matrix test(6, 12);

    try{
        test.set(6,1,1);
    }catch(const out_of_range&){
        rowThrow = true;
    }

    try{
        test.set(5,12,1);
    }catch(const out_of_range&){
        colThrow = true;
    }

    report("Test_Case_2_Set", rowThrow && colThrow);
}

// test case 1 for matrix operation =.
// checks = copies the shape and values and the two matrices are independent after
void Test_Case_1_Assign_Operator(){
    Matrix source(2,3);
    Matrix target(4,5);
    
    source.set(0, 0, 1);
    source.set(1, 2, 2);

    target = source;

    bool shapeCheck = (target.rows() == source.rows()) && (target.columns() == source.columns());
    bool valCheck = (target.get(0, 0) == source.get(0, 0)) &&(target.get(1, 2) == source.get(1, 2));

    target.set(0, 0, 0);

    bool independent = (target.get(0, 0) != source.get(0, 0));

    report("Test_Case_1_Assign_Operator", shapeCheck && valCheck && independent);
}

// test case 1 for matrix array constructor.
// checks a matrix made from a 2D array has the arrays values and shape
void Test_Case_1_ArrayConstructor(){
    int** arr = new int*[2];
    arr[0] = new int[1];
    arr[1] = new int[1];

    arr[0][0] = 1;
    arr[1][0] = 2;

    Matrix test(arr, 2, 1);

    bool numCheck = (test.get(0, 0) == arr[0][0]) && (test.get(1, 0) == arr[1][0]);
    bool rowCheck = test.rows() == 2;

    delete[] arr[0];
    delete[] arr[1];
    delete[] arr;

    report("Test_Case_1_ArrayConstructor", numCheck && rowCheck);
}

// test case 2 for matrix array constructor.
// checks passing a NULL array throws invalid_argument
void Test_Case_2_ArrayConstructor(){
    bool nullThrow = false;

    try{
        Matrix test(NULL, 2, 2);
    }catch(const invalid_argument&){
        nullThrow = true;
    }

    report("Test_Case_2_ArrayConstructor", nullThrow);
}

// test case 1 for matrix operation +.
// checks + adds two matrices value by value
void Test_Case_1_Plus(){
    bool pass = true;

    Matrix a(2,2), b(2,2);
    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            a.set(i,j,i+1);
            b.set(i,j,i+j+2);
        }
    }

    Matrix c = a+b;
    Matrix expected(2,2);
    expected.set(0,0, 3);
    expected.set(0,1, 4);
    expected.set(1,0, 5);
    expected.set(1,1, 6);

    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            if(c.get(i, j) != expected.get(i, j)) pass = false;
        }
    }

    report("Test_Case_1_Plus", pass);

}

// test case 1 for matrix operation -.
// checks - subtracts two matrices value by value
void Test_Case_1_Minus(){
    bool pass = true;

    Matrix a(2,2), b(2,2);
    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            a.set(i,j,i+1);
            b.set(i,j,i+j+2);
        }
    }

    Matrix c = a-b;
    Matrix expected(2,2);
    expected.set(0,0, -1);
    expected.set(0,1, -2);
    expected.set(1,0, -1);
    expected.set(1,1, -2);

    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            if(c.get(i, j) != expected.get(i, j)) pass = false;
        }
    }

    report("Test_Case_1_Minus", pass);

}

// test case 2 for matrix operation +.
// checks + throws invalid_argument for matrices of different sizes
void Test_Case_2_Plus(){
    bool sizeThrow = false;
    Matrix a(2,3), b(3,2);

    try{
        Matrix c = a+b;
    }catch(const invalid_argument&){
        sizeThrow = true;
    }

    report("Test_Case_2_Plus", sizeThrow);
}

// test case 2 for matrix operation -.
// checks - throws invalid_argument for matrices of different sizes
void Test_Case_2_Minus(){
    bool sizeThrow = false;
    Matrix a(2,3), b(3,2);

    try{
        Matrix c = a-b;
    }catch(const invalid_argument&){
        sizeThrow = true;
    }

    report("Test_Case_2_Minus", sizeThrow);
}

// test case 1 for matrix operation *.
// checks * gives the right result for a 2x3 times a 3x2 matrix
void Test_Case_1_Multiply(){
    bool pass = true;

    Matrix a(2,3), b(3,2);
    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            a.set(i,j,i * 3 + j+1);
        }
    }

    for(unsigned int i = 0; i < b.rows(); i++){
        for(unsigned int j = 0; j < b.columns(); j++){
            b.set(i, j, i * 2 + j + 7);
        }
    }
    
    Matrix c = a*b;
    Matrix expected(2,2);
    expected.set(0,0, 58);
    expected.set(0,1, 64);
    expected.set(1,0, 139);
    expected.set(1,1, 154);

    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < b.columns(); j++){
            if(c.get(i, j) != expected.get(i, j)) pass = false;
        }
    }

    report("Test_Case_1_Multiply", pass);

}

// test case 2 for matrix operation *.
// checks * throws invalid_argument when a's columns dont match b's rows
void Test_Case_2_Multiply(){
    bool sizeThrow = false;
    Matrix a(2,3), b(2,3);

    try{
        Matrix c = a*b;
    }catch(const invalid_argument&){
        sizeThrow = true;
    }

    report("Test_Case_2_Multiply", sizeThrow);
}

// test case 1 for matrix operation ~.
// checks ~ swaps the rows and columns
void Test_Case_1_Transpose(){
    bool pass = true;

    Matrix a(2,2);
    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            a.set(i,j,i * 2 + j+1);
        }
    }

    Matrix c = ~a;
    
    Matrix expected(2,2);
    expected.set(0,0, 1);
    expected.set(0,1, 3);
    expected.set(1,0, 2);
    expected.set(1,1, 4);

    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            if(c.get(i, j) != expected.get(i, j)) pass = false;
        }
    }

    report("Test_Case_1_Transpose", pass);

}

// test case 2 for matrix operation ~.
// checks ~ turns a 2x3 matrix into a 3x2 with the rows and columns swapped
void Test_Case_2_Transpose(){
    bool pass = true;

    Matrix a(2,3);
    for(unsigned int i = 0; i < a.rows(); i++){
        for(unsigned int j = 0; j < a.columns(); j++){
            a.set(i,j,i * 3 + j+1);
        }
    }

    Matrix c = ~a;

    Matrix expected(3,2);
    expected.set(0,0, 1);
    expected.set(0,1, 4);
    expected.set(1,0, 2);
    expected.set(1,1, 5);
    expected.set(2,0, 3);
    expected.set(2,1, 6);

    bool shapeCheck = (c.rows() == 3) && (c.columns() == 2);

    if(shapeCheck){
        for(unsigned int i = 0; i < c.rows(); i++){
            for(unsigned int j = 0; j < c.columns(); j++){
                if(c.get(i, j) != expected.get(i, j)) pass = false;
            }
        }
    }

    report("Test_Case_2_Transpose", shapeCheck && pass);
}

// test case 1 for matrix operation ==.
// checks == is true for identical matrices and false once a value changes
void Test_Case_1_Equals(){
    unsigned int i, j;
    Matrix a(2,2), b(2,2);

    for(i = 0; i < a.rows(); i++){
        for(j = 0; j < a.columns(); j++){
            a.set(i,j,i*2+j+1);
            b.set(i,j,i*2+j+1);
        }
    }

    bool identical = (a==b);

    a.set(0, 0, 2);
    bool different = !(a==b);

    report("Test_Case_1_Equals", identical && different);
}

// test case 2 for matrix operation ==.
// checks == is false for two matrices of different sizes
void Test_Case_2_Equals(){
    Matrix a(2,3), b(3,2);

    report("Test_Case_2_Equals", !(a==b));
}

// test case 1 for matrix function toStr().
// checks toStr() gives the right string format
void Test_Case_1_toString(){
    unsigned int i, j;
    Matrix a(2,2);

    for(i = 0; i < a.rows(); i++){
        for(j = 0; j < a.columns(); j++){
            a.set(i,j,i*2+j+1);
        }
    }

    bool identical = (a.toStr() == "1 2 \n3 4 \n");

    report("Test_Case_1_toString", identical);
}


// runs all the test cases
int main(){
    Test_Case_1_Constructor();
    Test_Case_1_Rows();
    Test_Case_1_Columns();
    Test_Case_1_Get();
    Test_Case_2_Get();
    Test_Case_1_Set();
    Test_Case_2_Set();
    Test_Case_1_CopyConstructor();
    Test_Case_1_Assign_Operator();
    Test_Case_1_ArrayConstructor();
    Test_Case_2_ArrayConstructor();
    Test_Case_1_Plus();
    Test_Case_1_Minus();
    Test_Case_2_Plus();
    Test_Case_2_Minus();
    Test_Case_1_Multiply();
    Test_Case_2_Multiply();
    Test_Case_1_Transpose();
    Test_Case_2_Transpose();
    Test_Case_1_Equals();
    Test_Case_2_Equals();
    Test_Case_1_toString();

    cout << endl << passed << " passed, " << failed << " failed" << endl;
    return 0;
}