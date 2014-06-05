package br.com.tales.sd.server.main.signature;

import br.com.tales.sd.server.main.application.Upload;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by tales on 28/05/14.
 */
public interface UploadService extends Remote,Runnable {
    public String getName() throws RemoteException;

    public String upload() throws RemoteException;


}
