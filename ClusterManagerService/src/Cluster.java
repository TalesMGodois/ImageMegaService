import auxiliar.HashTableRoute;
import auxiliar.Node;
import signature.ClusterService;
import signature.StorageService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by tales on 14/07/14.
 */
public class Cluster  extends UnicastRemoteObject implements ClusterService  {

    private static ArrayList<Node> repliques;
    private static ArrayList<Node> partitions;
    private Node activeR;
    private Node acttiveP;
    private HashTableRoute hst;

    private static final long serialVersionUID = -8550306338084922644L;

    public static void removeNode(Node node){

        if(node.getType().equals("partition")){
            partitions.remove(node);
        }else if(node.getType().equals("replique")){
            repliques.remove(node);
        }else{
            System.out.println("Nó não válido");
        }
    }

    protected Cluster() throws RemoteException {
        super();
        //Definir protocolo de cluster como 3 storages particionados
        this.hst = new HashTableRoute(3);
        repliques = new ArrayList<Node>();
        partitions = new ArrayList<Node>();
        Console console = new Console();
        Thread t = new Thread(console);
        t.start();

        SincronizeRepliques s = new SincronizeRepliques();
        Thread t2 = new Thread(s);
        t2.start();


    }

    public static ArrayList<Node> getRepliques(){
        return repliques;
    }

    public static ArrayList<Node> getPartitions(){
        return partitions;
    }

    @Override
    public boolean testNode(Node node) throws RemoteException, AlreadyBoundException, NotBoundException {
        boolean test = repliques.contains(node);
        try{
            Registry r = LocateRegistry.getRegistry(node.getIp(), node.getDoor());
            StorageService ss = (StorageService)  r.lookup("StorageService");
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void addStorage(Node node) {
        if(node.getType().equals("replique")){
            repliques.add(node);
            System.out.println("Replica Adcionada");


        }else if(node.getType().equals("partition")){
            partitions.add(node);
            System.out.println("Particao Adcionada" );

        }else{
            System.out.println(node.getName()+" Tentou se inscrever na porta:"+node.getDoor());
            System.out.println("Particao não adcionada");
        }

        System.out.println("Particões Ativas:" );
        for(Node s: partitions){
            System.out.println(""+ s.getName()+ ":("+ s.getIp()+ ":"+ s.getDoor()+ ")");
        }
        System.out.println("Replicas Ativas:");
        for(Node s: repliques){
            System.out.println( s.getName()+ "("+ s.getIp()+ ":"+ s.getDoor()+ ")");
        }
    }

    @Override
    public void sendup(byte[] img) {

    }

    public Node getNode(String imgName){
        System.out.println("ESCOLHER NÓ");
        System.out.println("Numero de Particões");

        if(partitions.size() == 1 ){
            return partitions.get(0);
        }else if(partitions.size() == hst.getSize()){
            int key = hst.createKey(imgName);
            int indice = hst.hashFunction(key);
            return partitions.get(indice);
        }else{
            System.out.println("Voce ainda não tem a quantidade de Particoes Estabelecidas");
            return null;
        }

    }

}
