package signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import auxiliar.Node;

/**
 * Created by tales on 14/07/14.
 */
public interface ClusterService extends Remote {
    public boolean testNode(Node node) throws RemoteException,AlreadyBoundException,NotBoundException;

    public Node getNode() throws RemoteException,AlreadyBoundException,NotBoundException;

    public void addStorage(Node node) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void sendup(byte[] img) throws RemoteException,AlreadyBoundException,NotBoundException;



}
