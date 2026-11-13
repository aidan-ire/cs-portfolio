import java.net.*;
import java.io.*;

/**
 * Handles one client connection for DateServer: sends the date, closes the
 * connection and reports which client it was.
 */
public class WorkerThread extends Thread{
    private Socket client;
    private int clientNumber;

    /**
     * @param client the connected socket returned by accept() in the main thread
     * @param clientNumber the count of this connection, starting from 1
     */
    public WorkerThread(Socket client, int clientNumber){
        this.client = client;
        this.clientNumber = clientNumber;
    }

    // this is the part of the practical 6 server that used to run in main
    public void run(){
        try{
            // pout is the output stream to the client
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

            pout.println(new java.util.Date().toString());

            client.close();
            System.out.println("finished processing client " + clientNumber);
        }catch(IOException ioe){
            System.err.println(ioe);
        }
    }
}
