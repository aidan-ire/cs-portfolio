#include <iostream>
using namespace std;

/*
 * length
 * Counts how many characters are in a string, not counting the '\0' at the end.
 * arguments:
 *   str - the string to measure
 * Returns:
 *   the number of characters before the first '\0', or 0 
 */
unsigned int length (const char* str){
    if(str == NULL) return 0;   // nothing to count

    unsigned int count = 0;
    // keep going until we hit the end of the string
    while(str[count] != '\0'){
        count++;
    }
    return count;
}

/*
 * copy
 * copies the string src (including its '\0') into dest. Since the real size of
 * a char* buffer can't be found, dest counts as big enough only if it already
 * holds a string at least as long as src.
 * Arguments:
 *   dest - the string to copy into (gets overwritten)
 *   src  - the string to copy from
 * Returns:
 *   dest after the copy, or NULL if dest isn't big enough or either argument
 *   is NULL (dest is left unchanged in that case)
 */
char* copy (char* dest, const char* src){
    if(src == NULL || dest == NULL) return NULL;
    // can't get the real buffer size from a char*, so dest needs to be able to hold a string at least as long as src
    // this was my assumption as per the brief
    if(length(dest) < length(src)) return NULL;

    unsigned int i = 0;
    // copy each character across
    while(src[i] != '\0'){
        dest[i] = src[i];
        i++;
    }
    dest[i] = '\0';   // end the string

    return dest;
}

/*
 * indexOf
 * finds where the character c first appears in str.
 * Arguments:
 *   c   - the character to look for (case sensitive)
 *   str - the string to search
 * Returns:
 *   the index of the first c in str, or -1 if c isn't found or str is NULL
 */
int indexOf (char c, const char* str){
    if(str == NULL) return -1;

    // checks each character until the end of the string
    for(int i = 0; str[i] != '\0'; i++){
        if(str[i] == c) return i;   // found it, returns index at from i
    }
    return -1;   // got to the end without finding it
}

/*
 * substring
 * Makes a new string from the characters of str starting at index i and going
 * up to index j - 1.
 * Arguments:
 *   i   - the starting index (included)
 *   j   - the ending index (not included)
 *   str - the string to take the substring from
 * Returns:
 *   a new string holding the substring, or NULL
 */
char* substring (int i, int j, const char* str){
    if(str == NULL) return NULL;
    // i and j can't be negative, i can't be past j, and j can't go past the end
    if(i < 0 || j < 0 || j < i || (unsigned int)j > length(str)) return NULL;

    int len = j - i;
    char* result = new char[len + 1];   // +1 for the '\0' 

    // copy the characters from i up to j-1
    for(int k = 0; k < len; k++){
        result[k] = str[i + k];
    }
    result[len] = '\0';

    return result;
}

/*
 * replace
 * Makes a new string that is the same as str but with every c replaced by p.
 * The original str is not changed.
 * Arguments:
 *   c   - the character to replace
 *   p   - the character to put in its place
 *   str - the string to work on
 * Returns:
 *   a new string with the replacements made, or NULL
 */
char* replace (char c, char p, const char* str){
    if(str == NULL) return NULL;

    unsigned int len = length(str);
    char* result = new char[len + 1];   // new string so the original isn't changed (needs delete[] after)

    for(unsigned int i = 0; i < len; i++){
        if(str[i] == c){
            result[i] = p;        // swap it
        }else{
            result[i] = str[i];   // keep it the same
        }
    }
    result[len] = '\0';

    return result;
}