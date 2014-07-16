import auxiliar.Node;
import auxiliar.Put;
import signature.StorageService;
import signature.UploadSlaveSig;
import signature.ClusterService;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Callable;

/**
 * Created by tales on 10/07/14.
 */
public class UploadSlave implements Callable<Boolean>, UploadSlaveSig {

    private static int works = 0;

    private Put put;
    private int id;

    public UploadSlave(String addrImg[]){
        works = works + 1;
        this.id = works;
        Put put = new Put(addrImg[1],addrImg[2]);
        put.setImage(put.getAddr());
        this.put = put;
    }

    public int getId(){
        return this.id;
    }

    public void setPut(Put put){
        this.put = put;
    }

    public boolean send(Put put)  throws RemoteException, AlreadyBoundException, NotBoundException {
        String serviceName = "storageService";
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.getRegistry("localhost", 2000);
        ClusterService cl = (ClusterService)  r.lookup("ClusterService");
        try{
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
//        StorageService send = (StorageService) r.lookup("StorageService");
//        System.out.println("realizando upload...");
//        if(put.getImage() != null){
//
//            boolean b = send.insertImage(put.getImage(),put.getName());
//
//            if(b == true){
//
//                System.out.println("Imagem Carregada com sucesso");
//                return true;
//            }
//            else{
//                System.out.println("Imagem Não carregada");
//                return false;
//            }
//        }else{
//            System.out.println("Não existe imagem");
//            return false;
//        }
    }

    @Override
    public Boolean call() throws Exception {
        return send(this.put);
    }
}
