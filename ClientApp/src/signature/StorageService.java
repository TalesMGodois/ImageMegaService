package signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;

/**
 * Created by tales on 28/06/14.
 */
public interface StorageService extends Remote {

    public Connection initBdConnection(String door, String name, String ip) throws RemoteException,AlreadyBoundException,NotBoundException;

    public boolean insertImage(byte[] img) throws RemoteException,AlreadyBoundException,NotBoundException;

    public String getImage() throws RemoteException,AlreadyBoundException,NotBoundException;

    public void closeConnection() throws RemoteException,AlreadyBoundException,NotBoundException;

}
