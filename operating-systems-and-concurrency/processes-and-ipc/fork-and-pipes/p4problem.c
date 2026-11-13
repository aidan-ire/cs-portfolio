#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
int pid; int i;

for (i = 1; i<=3; i++) {
   pid = fork();
   if (pid != 0) {
      printf("Process %d\n", pid); //parent prints child id
      execlp("/bin/ls", "ls", NULL);
      }
   }
}
