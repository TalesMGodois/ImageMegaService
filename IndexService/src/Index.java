import signature.IndexService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by tales on 05/07/14.
 */
public class Index implements IndexService{

    String itenName = "x";

    public Index() throws RemoteException, ClassNotFoundException{
        super();
    }

    @Override
    public String getName(String name) throws RemoteException, AlreadyBoundException, NotBoundException {
        return null;
    }

    @Override
    public void setName(String name) throws RemoteException, AlreadyBoundException, NotBoundException {


    }
}
