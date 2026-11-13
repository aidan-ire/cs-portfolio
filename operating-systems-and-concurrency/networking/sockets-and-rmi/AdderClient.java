import java.rmi.*;
import java.util.*;

/**
 * Client for the add-two-numbers service. Reads two integers from the
 * keyboard, sends them to the remote add method and prints what comes back.
 */
public class AdderClient{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input two numbers: ");

        if(!sc.hasNextInt()){
            System.out.println("Two whole numbers are needed");
            sc.close();
            return;
        }
        int a = sc.nextInt();

        if(!sc.hasNextInt()){
            System.out.println("Two whole numbers are needed");
            sc.close();
            return;
        }
        int b = sc.nextInt();
        sc.close();

        try{
            // 127.0.0.1 is the local host, the registry listens on port 1099 by default
            String host = "rmi://127.0.0.1/AdderServer";
            RemoteAdder adderServer = (RemoteAdder)Naming.lookup(host);

            // looks like a local method call, but the addition happens in the server process
            int sum = adderServer.add(a, b);
            System.out.println(a + " + " + b + " = " + sum);
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
