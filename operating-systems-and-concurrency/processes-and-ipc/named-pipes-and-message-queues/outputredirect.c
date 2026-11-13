#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

/*
same output as simpleoutput.c, but standard output is pointed at a named pipe
called fifo before the printf runs. dup always takes the lowest free descriptor,
so once descriptor 1 has been closed the copy of the pipe descriptor lands in
slot 1 and printf writes into the pipe without knowing anything has changed
eg. open -> fd 3 is the pipe, close(1) -> slot 1 free, dup(3) -> slot 1 is the pipe
*/
int main(){
    int fd;

    // create the pipe the same way as namedpipe.c, unless it is already there
    if(access("fifo", F_OK) == -1){
        if(mknod("fifo", 010600, 0) == -1){
            perror("mknod");
            exit(1);
        }
    }

    fd = open("fifo", O_WRONLY);    // blocks here until a reader (cat fifo) opens the pipe
    if(fd < 0){
        perror("open");
        exit(1);
    }
    if(close(1) < 0){
        perror("close");
        exit(1);
    }
    if(dup(fd) < 0){
        perror("dup");
        exit(1);
    }

    printf("Aidan Hughes\n");
    return 0;
}
