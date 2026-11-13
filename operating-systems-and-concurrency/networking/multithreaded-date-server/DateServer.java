// This is the Server code, save as DateServer.java
import java.net.*;
import java.io.*;

public class DateServer {
   public static void main(String[] args) throws
                                             IOException {
      try {
         // This creates a listener socket
         ServerSocket sock = new ServerSocket(6013);
         int connectionCount = 0;

         // the main thread only listens and accepts. each connection is handed
         // to its own worker thread so a slow client can't hold up the others
         while (true) {
            Socket client = sock.accept();
            connectionCount++;

            WorkerThread worker = new WorkerThread(client, connectionCount);
            worker.start();
         }
      }
      catch (IOException ioe) {
         System.err.println(ioe);
      }
   }
}
