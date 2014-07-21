package signature;

import auxiliar.Node;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 28/06/14.
 */
public interface StorageService extends Remote {
    //Metodos para salvar e baixar imagens da base de dados
    public boolean insertImage(byte[] img, String name) throws RemoteException,AlreadyBoundException,NotBoundException;

    public byte[] getImage(String name) throws RemoteException,AlreadyBoundException,NotBoundException;

    public Node getNode() throws RemoteException,AlreadyBoundException,NotBoundException;

    public void closeConnection() throws RemoteException,AlreadyBoundException,NotBoundException;

    //Métodos para a comnicaćão com o Cluster
    public boolean subscribe() throws RemoteException,AlreadyBoundException,NotBoundException;




}
