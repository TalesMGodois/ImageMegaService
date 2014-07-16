import auxiliar.Node;
import signature.ClusterService;
import signature.StorageService;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 16/07/14.
 */
public class SincronizeRepliques implements Runnable {
    @Override
    public void run() {
        try{
            testNode();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testNode() throws RemoteException,NotBoundException{
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());
        System.out.println(Cluster.getPartitions().size());
        if(Cluster.getPartitions().size() == 0){
            System.out.println("############## NÃO EXISTEM PARTICOES ATIVAS ###############");
        }else{
            for(Node s: Cluster.getPartitions()){
                try{
                    Registry r = LocateRegistry.getRegistry(s.getIp(), s.getDoor());
                    StorageService sv = (StorageService)  r.lookup("StorageService");
                    System.out.println("Storage (//"+s.getIp()+":"+s.getDoor()+ ") Está OK");
                }catch (Exception e){
//                    e.printStackTrace();
                    Cluster.removeNode(s);
                    System.out.println("#######Falha ao conectar neste nó");
                    System.out.println("REMOVENDO STORAGE (//" + s.getIp() + ":" + s.getDoor()+ ")");
                }
            }
        }

        try{
            Thread.sleep(2000);
            testNode();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
