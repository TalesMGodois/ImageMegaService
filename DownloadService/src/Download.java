import signature.DownloadService;
import signature.StorageService;

import java.io.*;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tales on 01/07/14.
 */
public class Download extends UnicastRemoteObject implements DownloadService {

    private static final long serialVersionUID = -8550306338084922644L;

    String ip = "localhost";

    public Download() throws RemoteException, AlreadyBoundException{
        super();
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException, NotBoundException {
        return null;
    }

    @Override
    public byte[] getImage(String name) throws RemoteException, AlreadyBoundException, NotBoundException {
        String serviceName = "StorageService";
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());
        Registry r = LocateRegistry.getRegistry(this.ip, 2015);
        StorageService storage = (StorageService) r.lookup(serviceName);
        System.out.println("tentando realizar download");
        byte[] bt = storage.getImage(name);

        return bt;
    }

    @Override
    public void make(String str) throws RemoteException, AlreadyBoundException, NotBoundException {
        System.out.println("Parabens por comecar a implementar o download");
    }
}
