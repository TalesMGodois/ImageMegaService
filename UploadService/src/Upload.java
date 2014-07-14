import auxiliar.Put;
import signature.UploadService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tales on 28/06/14.
 */
public class Upload extends UnicastRemoteObject implements UploadService {

    private static final long serialVersionUID = -8550306338084922644L;

    private String ip = "localhost";



    private int door;
    public Upload() throws RemoteException, AlreadyBoundException{
        super();
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException {
        return null;
    }

    @Override
    public void make(String img[]) throws RemoteException, java.rmi.AlreadyBoundException, NotBoundException {
        Put put;
        try{
            put = new Put(img[2],img[3]);
            put.setImage(put.getAddr());

            if(put.getImage() != null){
                UploadSlave up = new UploadSlave();
                up.setPut(put);
                Thread t = new Thread(up);
                t.start();
            }else{
                System.out.println("Não existe imagem a ser enviada");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Imagem não pode ser enviada sem nome");
        }

    }
}
