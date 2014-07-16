import auxiliar.Node;
import signature.ClusterService;
import signature.StorageService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by tales on 28/06/14.
 */
public class Storage extends UnicastRemoteObject implements StorageService {
    ConnectionFactory con;
    private static final long serialVersionUID = -8550306338084922644L;

    private Node node;

    public Storage(ConnectionFactory connection) throws RemoteException, ClassNotFoundException {
        super();
        this.node = new Node();
        this.node.upNode(Server.getId(),Server.getDoor(),Server.getIp(),Server.getName(),Server.getType());
        this.con = connection;
    }

    @Override
    public boolean insertImage(byte[] img, String name) throws RemoteException,AlreadyBoundException,NotBoundException{
        System.out.println(img.toString());

        if(this.con.insert(name,img)){
            return true;
        }else return false;
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
    public boolean testNode() throws RemoteException, AlreadyBoundException, NotBoundException {
        return false;
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
