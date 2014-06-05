package br.com.tales.sd.server.main.signature;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 28/05/14.
 */
public interface DownloadService extends Remote,Runnable {
    public String getName() throws RemoteException;

    public byte[] getImage() throws RemoteException;

}
