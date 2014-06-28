import signature.UploadService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tales on 28/06/14.
 */
public class Upload extends UnicastRemoteObject implements UploadService {

    private static final long serialVersionUID = -8550306338084922644L;

    public Upload() throws RemoteException, AlreadyBoundException{
        super();
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException {
        return null;
    }

    @Override
    public void make(String name) throws RemoteException, AlreadyBoundException {
        System.out.println("Realizar upload");
    }
}
