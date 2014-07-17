package signature;

import auxiliar.Node;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 14/i07/14.
 */
public interface ClusterService extends Remote {
    public boolean testNode(Node node) throws RemoteException,AlreadyBoundException,NotBoundException;

    public Node getNode(String imgName) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void addStorage(Node node) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void sendup(byte[] img) throws RemoteException,AlreadyBoundException,NotBoundException;



}
