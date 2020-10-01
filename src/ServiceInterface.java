import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface {
    void backup(String filepath, int replicationDegree) throws RemoteException;
    void restore(String filepath) throws RemoteException;
    void delete(String filepath) throws RemoteException;
    void reclaim(int space) throws RemoteException;
    String state() throws RemoteException;

}
