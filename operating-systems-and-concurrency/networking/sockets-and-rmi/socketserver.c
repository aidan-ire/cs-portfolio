/*************** Server Code ****************/
#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), bind(), and connect() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_ntoa() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */

#define MAXPENDING 5    /* Maximum outstanding connection requests */
#define RCVBUFSIZE 50   /* Size of receive buffer */

int main(int argc, char *argv[])
{
    int servSock;                    /* Socket descriptor for server */
    int clntSock;                    /* Socket descriptor for client */
    struct sockaddr_in echoServAddr; /* Local address */
    struct sockaddr_in echoClntAddr; /* Client address */
    unsigned short echoServPort;     /* Server port */
    unsigned int clntLen;            /* Length of client address data structure */
    char echoBuffer[RCVBUFSIZE];     /* Buffer for receiving client's msg string */
    int recvMsgSize;                 /* Size of received message */
    char *echoString;                /* Server's reply to client */
    unsigned int echoStringLen;      /* Length of server's reply string */

    echoString = "server is alive, how are you?";   /* Server's reply to client */
    echoStringLen = 29;
    echoServPort = 8093;  /* local port on which server is going to listen */

    /* Create local TCP/IP socket for incoming connections */
    servSock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
    if(servSock < 0){
        perror("Server: could not create socket");
        exit(1);
    }

    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort);      /* Local port */

    /* Bind local socket to the desired server port address */
    // this is the call that fails when a second server is started, the socket
    // itself is created fine but the port already belongs to the first server
    if(bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0){
        perror("Server: could not bind to port 8093");
        close(servSock);
        exit(1);
    }

    /* Mark the socket so it will listen for incoming connections */
    if(listen(servSock, MAXPENDING) < 0){
        perror("Server: could not listen");
        close(servSock);
        exit(1);
    }

    for (;;) /* Run forever */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr);

        /* Blocking wait for a client to connect */
        clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr,&clntLen);
        /* clntSock is connected to a client! */
        printf("Server: Handling client %s\n",inet_ntoa(echoClntAddr.sin_addr));

        /* Receive message from client */
        recvMsgSize = recv(clntSock, echoBuffer, RCVBUFSIZE - 1, 0);
        if(recvMsgSize < 0) recvMsgSize = 0;
        echoBuffer[recvMsgSize] = '\0';   // recv doesn't terminate the string, printf needs it
        printf("Server: Received msg-> %s\n",echoBuffer);

        /* Send response message back to client */
        send(clntSock, echoString, echoStringLen, 0);
        printf("Server: Sent Reply-> %s\n",echoString);
        close(clntSock);    /* Close client socket */
    }
}
