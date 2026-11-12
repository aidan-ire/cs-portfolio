#include <iostream>
#include "string_utils.cpp"
using namespace std;

// ---------- tests ----------
// each test prints what it expected and what it actually got

// length

void length_name_test_case_1(){
    cout << "length_name_test_case_1: expected 6, got " << length("string") << endl;
}

void length_name_test_case_2(){
    cout << "length_name_test_case_2: expected 6, got " << length("string\0") << endl;
}

void length_name_test_case_3(){
    cout << "length_name_test_case_3: expected 0, got " << length("") << endl;
}

void length_name_test_case_4(){
    cout << "length_name_test_case_4: expected 1, got " << length("a") << endl;
}

// spaces count as characters too
void length_name_test_case_5(){
    cout << "length_name_test_case_5: expected 11, got " << length("hello world") << endl;
}

// copy

void copy_name_test_case_1(){
    char dest[] = "xxxxxx";
    copy(dest, "string");
    cout << "copy_name_test_case_1: expected string, got " << dest << endl;
}

// dest too small, should return NULL and not touch dest
void copy_name_test_case_2(){
    char dest[] = "abc";
    char* result = copy(dest, "string");
    cout << "copy_name_test_case_2: expected NULL, got " << (result ? result : "NULL") << endl;
}

// dest bigger than needed, the '\0' should cut off the leftover x's
void copy_name_test_case_3(){
    char dest[] = "xxxxxxxxxx";
    copy(dest, "abc");
    cout << "copy_name_test_case_3: expected abc, got " << dest << endl;
}

void copy_name_test_case_4(){
    char dest[] = "xyz";
    char* result = copy(dest, NULL);
    cout << "copy_name_test_case_4: expected NULL, got " << (result ? result : "NULL") << endl;
}

void copy_name_test_case_5(){
    char* result = copy(NULL, "abc");
    cout << "copy_name_test_case_5: expected NULL, got " << (result ? result : "NULL") << endl;
}

// indexOf

void indexOf_name_test_case_1(){
    cout << "indexOf_name_test_case_1: expected 3, got " << indexOf('i', "string") << endl;
}

void indexOf_name_test_case_2(){
    cout << "indexOf_name_test_case_2: expected -1, got " << indexOf('a', "string") << endl;
}

// should give the first r, not the later ones
void indexOf_name_test_case_3(){
    cout << "indexOf_name_test_case_3: expected 1, got " << indexOf('r', "error") << endl;
}

void indexOf_name_test_case_4(){
    cout << "indexOf_name_test_case_4: expected 0, got " << indexOf('s', "string") << endl;
}

// upper and lower case are different characters
void indexOf_name_test_case_5(){
    cout << "indexOf_name_test_case_5: expected -1, got " << indexOf('S', "string") << endl;
}

// substring

void substring_name_test_case_1(){
    char* result = substring(0, 3, "string");
    cout << "substring_name_test_case_1: expected str, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

void substring_name_test_case_2(){
    char* result = substring(3, 6, "string");
    cout << "substring_name_test_case_2: expected ing, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

// j is past the end of the string, should return NULL
void substring_name_test_case_3(){
    char* result = substring(0, 7, "string");
    cout << "substring_name_test_case_3: expected NULL, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

// i is after j, should return NULL
void substring_name_test_case_4(){
    char* result = substring(3, 1, "string");
    cout << "substring_name_test_case_4: expected NULL, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

// i is negative, should return NULL
void substring_name_test_case_5(){
    char* result = substring(-1, 2, "string");
    cout << "substring_name_test_case_5: expected NULL, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

// replace

void replace_name_test_case_1(){
    char* result = replace('i', 'x', "string");
    cout << "replace_name_test_case_1: expected strxng, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

void replace_name_test_case_2(){
    char* result = replace('s', 'z', "sass");
    cout << "replace_name_test_case_2: expected zazz, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

// c not in the string, should get a copy back
void replace_name_test_case_3(){
    char* result = replace('a', 'x', "string");
    cout << "replace_name_test_case_3: expected string, got " << (result ? result : "NULL") << endl;
    delete[] result;
}

// the original string shouldn't be changed
void replace_name_test_case_4(){
    char original[] = "banana";
    char* result = replace('a', 'o', original);
    cout << "replace_name_test_case_4: expected bonono and banana, got " << (result ? result : "NULL")
         << " and " << original << endl;
    delete[] result;
}

void replace_name_test_case_5(){
    char* result = replace('a', 'b', NULL);
    cout << "replace_name_test_case_5: expected NULL, got " << (result ? result : "NULL") << endl;
    delete[] result;
}
