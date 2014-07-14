package signature;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import auxiliar.Put;

/**
 * Created by tales on 13/07/14.
 */
public interface UploadSlaveSig {

    public void setPut(Put put);

    public boolean send(Put put) throws RemoteException, AlreadyBoundException, NotBoundException;
}
