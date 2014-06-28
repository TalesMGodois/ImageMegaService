import signature.StorageService;
import signature.UploadService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tales on 28/06/14.
 */
public class Upload extends UnicastRemoteObject implements UploadService {

    private String ame = "UploadService";

    private static final long serialVersionUID = -8550306338084922644L;
    private String ip;
    private int door;
    public Upload() throws RemoteException, AlreadyBoundException{
        super();
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException {
        return null;
    }

    @Override
    public void make(String name) throws RemoteException, AlreadyBoundException {
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());
        Registry r = LocateRegistry.getRegistry(this.ip, this.door);
        StorageService send = (StorageService) r.lookup(this.name);
        send.initBdConnection(3001,"imageStorage","localhost");
        System.out.println("Realizar upload");

    }
}
