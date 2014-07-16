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
import java.util.List;

/**
 * Created by tales on 14/07/14.
 */
public class Cluster  extends UnicastRemoteObject implements ClusterService  {

    private static ArrayList<Node> repliques;
    private static ArrayList<Node> partitions;
    private Node activeR;
    private Node acttiveP;

    private static final long serialVersionUID = -8550306338084922644L;

    protected Cluster() throws RemoteException {
        super();
        repliques = new ArrayList<Node>();
        partitions = new ArrayList<Node>();
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
    public String testeCLuster() throws RemoteException, AlreadyBoundException, NotBoundException {
        System.out.println("teste aceito");
        return "TESTE ACEITO";
    }

    @Override
    public boolean createPartiion(String name, String type, String ip, int door) {
        return false;
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

    public Node getNode(){
        System.out.println();
        //Tratar o que for necessario para escolher um nó, tanto a partition e a replica
        return partitions.get(1);
    }

    public void consoleView (){

        if(partitions.size() == 0 && repliques.size() == 0){
            System.out.println("Sistema não está pronto para uso, Não existem particoes ou replicas");
        }else{
            if(partitions.size() != 0){
                System.out.println("Particões ATIVAS" );
                for(Node s: partitions){
                    System.out.println(""+ s.getName()+ ":("+ s.getIp()+ ":"+ s.getDoor()+ ")");
                }
            }if(repliques.size() !=0){
                System.out.println("Replicas ATIVAS:");
                for(Node s: repliques){
                    System.out.println( s.getName()+ "("+ s.getIp()+ ":"+ s.getDoor()+ ")");
                }
            }


        }
    }
}
