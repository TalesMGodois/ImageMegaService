package signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 28/05/14.
 */
public interface DownloadService extends Remote {
    public String getName() throws RemoteException,AlreadyBoundException,NotBoundException;

    public byte[] getImage(String name) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void make(String str) throws RemoteException,AlreadyBoundException,NotBoundException;

}
