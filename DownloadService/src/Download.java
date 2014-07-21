import auxiliar.Node;
import signature.ClusterService;
import signature.DownloadService;
import signature.StorageService;

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
    public byte[] getImage(String name) throws RemoteException, AlreadyBoundException,NotBoundException  {
        String serviceName = "StorageService";
        System.setProperty("java.security.policy", "java.policy");
        Registry r = LocateRegistry.getRegistry("127.0.1.1", 2000);
        ClusterService cl = (ClusterService)  r.lookup("ClusterService");
        Node nd = cl.getNode(name);
        System.out.println("tentando realizar download");
        Registry p = LocateRegistry.getRegistry(nd.getIp(), nd.getDoor());
        StorageService storage = (StorageService) p.lookup(serviceName);
        byte[] bt = storage.getImage(name);
        if(bt != null){
            System.out.println("Imagem Encontrada");
        }else{
            System.out.println("Imagem Não encontrada");
        }
        return bt;
    }

    @Override
    public void make(String str) throws RemoteException, AlreadyBoundException, NotBoundException {
        System.out.println("Parabens por comecar a implementar o download");
    }
}
