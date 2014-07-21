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

    private int id;

    private Put put;

    public UploadSlave(Put put,int id){
        works = works + 1;
        this.id = id;
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

        try{
            Registry r = LocateRegistry.getRegistry("localhost", 2000);
            ClusterService cl = (ClusterService)  r.lookup("ClusterService");
            Node nd = cl.getNode(put.getName());
            if(nd != null ){
                Registry l = LocateRegistry.getRegistry(nd.getIp(),nd.getDoor());
                StorageService send = (StorageService) l.lookup("StorageService");
                System.out.println("realizando upload...");
                System.out.println("REALIZAR UPLOAD No SERVIDOR :" +nd.getIp()+":"+ nd.getDoor());
                if(put.getImage() != null){

                    boolean b = send.insertImage(put.getImage(),put.getName());

                    if(b == true){

                        System.out.println("Imagem Carregada com sucesso");
                        Upload.removeNode(this);
                        return true;
                    }
                    else{
                        System.out.println("Imagem Não carregada");
                        Upload.removeNode(this);
                        return false;
                    }
                }else{
                    System.out.println("Não existe imagem");
                    Upload.removeNode(this);
                    return false;
                }
            }else{
                System.out.println("Não existe nó válido");
                Upload.removeNode(this);
                return false;
            }
        }catch (Exception e){
            Upload.removeNode(this);
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean call() throws Exception {
        return send(this.put);
    }
}
