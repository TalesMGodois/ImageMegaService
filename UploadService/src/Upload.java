import auxiliar.Put;
import signature.UploadService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Queue;
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

    private static ArrayList<UploadSlave> uploads;

    private String ip = "localhost";

    private int door;

    private int maxPool = 50;


    public Upload() throws RemoteException, AlreadyBoundException{
        super();
        uploads     = new ArrayList<UploadSlave>();
        futures     = new Future[maxPool];
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException {
        return null;
    }

    public static void removeNode(UploadSlave up){
        try{
            uploads.remove(up);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean make(Put put) throws RemoteException, java.rmi.AlreadyBoundException, NotBoundException {

        ExecutorService tpes = Executors.newCachedThreadPool();

        if (uploads.size() <= maxPool) {
            System.out.println("Criar thread de upload");
            UploadSlave ups = new UploadSlave(put,uploads.size());
            uploads.add(ups);
            futures[ups.getId()] = tpes.submit(uploads.get(ups.getId()));
            try {
                boolean b = futures[ups.getId()].get();

                return b;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            } catch (ExecutionException e) {
                e.printStackTrace();
                return false;
            }


        }else{
            return false;
        }
    }
}
