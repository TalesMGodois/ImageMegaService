import auxiliar.Node;
import com.mysql.jdbc.Connection;
import signature.ClusterService;
import signature.StorageService;

import java.awt.event.ActionListener;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by tales on 28/06/14.
 */
public class Storage extends UnicastRemoteObject implements StorageService {
    private static ConnectionFactory con;
    private static final long serialVersionUID = -8550306338084922644L;

    private static Node node;

    private static ArrayList<ThreadSave> sends;

    private static Future<Boolean> futures[];

    private static int numberOfConnections;

    private int maxPool = 50;

    public Storage(ConnectionFactory connection) throws RemoteException, ClassNotFoundException {
        super();
        sends = new ArrayList<ThreadSave>();
        futures = new Future[this.maxPool];
        this.node = new Node();
        this.node.upNode(Server.getId(),Server.getDoor(),Server.getIp(),Server.getName(),Server.getType());
        this.con = connection;

    }

    public static ConnectionFactory getCon(){
        return con;
    }

    public static void clearThread(ThreadSave td){
        sends.remove(td);
    }

    public static void sincronizeNode(){
        node.setNumberOfActions(sends.size());
    }

    @Override
    public boolean insertImage(byte[] img, String name) throws RemoteException,AlreadyBoundException,NotBoundException{

        ExecutorService tpes = Executors.newCachedThreadPool();
        if (sends.size() <= this.maxPool) {
            System.out.println("Criar thread de upload");
            ThreadSave ups = new ThreadSave(img, name, sends.size());
            sends.add(ups);
            futures[ups.getId()] = tpes.submit(sends.get(ups.getId()));
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

    @Override
    public byte[] getImage(String name) throws RemoteException,AlreadyBoundException,NotBoundException {
        byte[] img = con.download(name);
        if(img == null){
            System.out.println("Não foi possivel realizar o download de " +name);
            return null;
        }else{
            return img;
        }

    }

    @Override
    public  Node getNode() throws RemoteException, AlreadyBoundException, NotBoundException {
        return this.node;
    }

    @Override
    public void closeConnection() {
//        Fecha conexao se necessarioi
    }

    @Override
    public boolean subscribe() throws RemoteException, AlreadyBoundException, NotBoundException {
        String serviceName = "storageService";
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());
        Registry r = LocateRegistry.getRegistry("localhost",2000);

        ClusterService cl = (ClusterService)  r.lookup("ClusterService");
        cl.addStorage(this.node);

        return false;
    }
}
