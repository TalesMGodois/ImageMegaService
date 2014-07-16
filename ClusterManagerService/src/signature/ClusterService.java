package signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

import auxiliar.Node;
/**
 * Created by tales on 14/07/14.
 */
public interface ClusterService extends Remote {
    public boolean testNode(Node node) throws RemoteException,AlreadyBoundException,NotBoundException;

    public String testeCLuster() throws RemoteException,AlreadyBoundException,NotBoundException;

    public boolean createPartiion(String name,String type,String ip,int door) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void addStorage(Node node) throws RemoteException,AlreadyBoundException,NotBoundException;

    public void sendup(byte[] img) throws RemoteException,AlreadyBoundException,NotBoundException;



}
