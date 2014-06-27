package br.com.tales.sd.server.main.application;

import br.com.tales.sd.server.main.signature.DownloadService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tales on 28/05/14.
 */
public class Download extends UnicastRemoteObject implements DownloadService{

    public Download() throws RemoteException, AlreadyBoundException {
        super();
    }

    @Override
    public String getName() throws RemoteException {
        return null;
    }

    @Override
    public byte[] getImage() throws RemoteException {
        return new byte[0];
    }

    @Override
    public void make(String str) {
        System.out.println("QUero Baixar:  "+ str);
    }
}
