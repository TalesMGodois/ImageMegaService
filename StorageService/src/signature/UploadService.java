package signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 28/06/14.
 */
public interface UploadService extends Remote {

    public String getName() throws RemoteException,AlreadyBoundException,NotBoundException;

    public boolean make(String[] name) throws RemoteException, NotBoundException, java.rmi.AlreadyBoundException;

}
