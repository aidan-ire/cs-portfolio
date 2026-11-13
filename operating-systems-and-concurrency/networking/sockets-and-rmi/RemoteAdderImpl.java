import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server side of the add-two-numbers service. Creates one adder object and
 * registers it with the rmiregistry under the name AdderServer.
 */
public class RemoteAdderImpl extends UnicastRemoteObject implements RemoteAdder{
    public RemoteAdderImpl() throws RemoteException{}

    public static void main(String[] args){
        try{
            RemoteAdder adderServer = new RemoteAdderImpl();
            Naming.rebind("AdderServer", adderServer);
            System.out.println("AdderServer registered, waiting for calls");
        }catch(Exception e){
            System.err.println(e);
        }
    }

    // this runs in the server's JVM, the client only gets the result back
    public int add(int a, int b) throws RemoteException{
        System.out.println("add(" + a + ", " + b + ") called");
        return a + b;
    }
}
