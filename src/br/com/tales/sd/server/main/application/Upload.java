package br.com.tales.sd.server.main.application;


import java.nio.channels.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tales on 28/05/14.
 */
public class Upload extends UnicastRemoteObject {

    private static final long serialVersionUID = -8550306338084922644L;

    public Upload() throws RemoteException, AlreadyBoundException{
        super();
    }

    public String getName() {
        return null;
    }


    public String make(String it) {
        System.out.println("Mostrando uso do : " + it);

//        UploadSlave up = new UploadSlave(obj);
//        UploadSlave up2 = new UploadSlave(obj);
//        Thread t = new Thread(up);
//        Thread t2 = new Thread(up2);
//        t.start();
//        t2.start();

        return it;
    }
}
