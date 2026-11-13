import java.rmi.*;

/**
 * Remote interface for the add-two-numbers service. Anything a client is
 * allowed to call on the server has to be declared here.
 */
public interface RemoteAdder extends Remote{
    /**
     * Adds two integers on the server.
     * @param a the first number
     * @param b the second number
     * @return a + b
     * @throws RemoteException if the call can't reach the server
     */
    public abstract int add(int a, int b) throws RemoteException;
}
