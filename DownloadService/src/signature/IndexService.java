package signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 05/07/14.g
 */
public interface IndexService extends Remote {
    public String getName(String name) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void setName(String name) throws RemoteException,AlreadyBoundException,NotBoundException;
}
