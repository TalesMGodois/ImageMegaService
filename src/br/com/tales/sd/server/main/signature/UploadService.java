package br.com.tales.sd.server.main.signature;

import java.nio.channels.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 28/05/14.
 */
public interface UploadService extends Remote{


    public String getName() throws RemoteException,AlreadyBoundException;

    public String make(String name) throws RemoteException,AlreadyBoundException;

}
