/* POSIX message queues: Linux only (no <mqueue.h> on macOS). Link with -lrt */
#include <fcntl.h> /* for using the the O_mode constants */
#include <sys/stat.h> /* for using other mode constants */
#include <mqueue.h> /* Defines the mqueue functions */
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/*
mqueuewriter.c changed so the five messages and their priorities are typed in by
the user instead of being generated in a loop. the queue hands messages back
highest priority first, so the order they are typed in doesn't decide the order
mqueuereader prints them in
*/
int main (int argc, char *argv[]) {
   int modeflags = O_CREAT | O_WRONLY;
   mode_t permissions = 0600; /* Read/Write for owner only */
   struct mq_attr attr;
   mqd_t mq;

   char qname [20];
   int max_size =512;
   char buffer[max_size];
   int priority;
   int i, c, len;

   strcpy(qname, "/test_queue");
   attr.mq_flags = 0; /* Blocking allowed mode */
   attr.mq_maxmsg = 10;
   attr.mq_msgsize = max_size;

   mq = mq_open(qname, modeflags, permissions, &attr);
   if (mq < 0) {
      printf("Couldn't create queue %s\n",qname);
      exit(-1);
   }
   printf("Opened a message queue called %s and got descriptor %d \n",
qname,mq);

   for(i = 1; i <= 5; i++){
       memset(buffer,0, max_size); /*Clear all buffer locations to 0 */

       printf("Enter message %d: ", i);
       if(fgets(buffer, max_size, stdin) == NULL){
           printf("\nInput ended early, %d messages sent\n", i - 1);
           break;
       }
       len = strlen(buffer);
       if(len > 0 && buffer[len - 1] == '\n') buffer[--len] = 0;   // fgets keeps the newline, the message shouldn't

       printf("Enter its priority: ");
       if(scanf("%d", &priority) != 1 || priority < 0){
           printf("Priority must be a whole number, 0 or higher. Using 0\n");
           priority = 0;
       }
       // scanf leaves the rest of the line (at least the newline) behind,
       // collect it here or the next fgets would read an empty message
       while((c = getchar()) != '\n' && c != EOF);

       printf("Sending message : '%s' to message queue\n",buffer);
       if(mq_send(mq, buffer, len, priority) < 0) perror("mq_send");
   }

   if (!mq_close(mq))
      printf("Closed the queue\n");
}
