package br.com.tales.sd.server.main.application;

import br.com.tales.sd.server.main.signature.DownloadService;

import java.rmi.RemoteException;

/**
 * Created by tales on 28/05/14.
 */
public class Download implements DownloadService, Runnable{
    private Download(){}

    public static Download INSTANCE;

    public static Download self(){
        if(INSTANCE == null){
            INSTANCE = new Download();
        }
        return  INSTANCE;
    }


    public static void start(){
        Thread t = new Thread(Download.INSTANCE);
        t.start();
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
    public void run() {

    }
}
