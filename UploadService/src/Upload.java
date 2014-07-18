import auxiliar.Put;
import signature.UploadService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by tales on 28/06/14.
 */
public class Upload extends UnicastRemoteObject implements UploadService {

    private static final long serialVersionUID = -8550306338084922644L;

    private static Future<Boolean> futures[];

    private static UploadSlave uploads[];

    private String ip = "localhost";

    private int door;

    private int maxPool = 50;


    public Upload() throws RemoteException, AlreadyBoundException{
        super();
        uploads     = new UploadSlave[this.maxPool];
        futures     = new Future[maxPool];
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException {
        return null;
    }

    @Override
    public boolean make(Put put) throws RemoteException, java.rmi.AlreadyBoundException, NotBoundException {
        try{
            ExecutorService tpes = Executors.newCachedThreadPool();
            UploadSlave ups = new UploadSlave(put);
            int upId = ups.getId();
            uploads[upId] = ups;
            futures[upId] = tpes.submit(uploads[upId]);
            try{

                boolean b = futures[upId].get();
                return  b;
            }catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return false;
            }

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Imagem não pode ser enviada sem nome");
            return false;
        }

    }
}
