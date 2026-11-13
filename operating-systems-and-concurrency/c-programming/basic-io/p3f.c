#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

/*
my own version of cat. it asks for a file name, opens the file and copies it to
standard output 10 bytes at a time using read and write. read gives back how many
bytes it actually got, so a block shorter than 10 means the end of the file has
been reached and the loop stops
eg. a 23 byte file -> read gives 10, 10, 3 and the loop ends after the 3
*/
int main(){
    char filename[100];
    char buffer[10];
    int fd, n;

    printf("Enter the name of the file to print\n");
    fflush(stdout);                 // the rest of the output uses write, so push the prompt out first
    if(scanf("%99s", filename) != 1){
        fprintf(stderr, "No file name was entered\n");
        exit(1);
    }

    fd = open(filename, O_RDONLY);
    if(fd < 0){
        perror(filename);
        exit(1);
    }

    do{
        n = read(fd, buffer, 10);   /* Read 10 chars from file */
        if(n < 0){
            perror("read");
            close(fd);
            exit(1);
        }
        if(write(1, buffer, n) < 0){   /* Write chars to text terminal */
            perror("write");
            close(fd);
            exit(1);
        }
    }while(n == 10);                /* keep reading until n < 10 */

    if(close(fd) < 0){
        perror("close");
        exit(1);
    }
    return 0;
}
