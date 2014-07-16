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

/**
 * Created by tales on 10/07/14.
 */
public class UploadSlave implements Runnable, UploadSlaveSig {

    private int slaves = 0;

    private Put put;

    private boolean status;

    private int door = 2015;
    private String ip = "localhost";
    public UploadSlave(String ip,int door){

        this.ip = ip;
        this.door = door;
    }

    public UploadSlave(){}

    @Override
    public void run() {
        this.slaves++;
        //envia imagem
        System.out.println("Enviando imagem");
        try{
            send(this.put);
        }catch (AlreadyBoundException e){
            e.printStackTrace();
        }catch (RemoteException e){
            e.printStackTrace();
        }catch (NotBoundException e){
            e.printStackTrace();
        }
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setPut(Put put){
        this.put = put;
    }

    public boolean send(Put put)  throws RemoteException, AlreadyBoundException, NotBoundException {
        String serviceName = "storageService";
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.getRegistry(this.ip, this.door);
        ClusterService cl = (ClusterService)  r.lookup("ClusterService");

        String sl = cl.testeCLuster();
        System.out.println(sl);
        StorageService send = (StorageService) r.lookup("StorageService");
        System.out.println("realizando upload...");
        if(put.getImage() != null){

            boolean b = send.insertImage(put.getImage(),put.getName());

            if(b == true){

                System.out.println("Imagem Carregada com sucesso");
                return true;
            }
            else{
                System.out.println("Imagem Não carregada");
                return false;
            }
        }else{
            System.out.println("Não existe imagem");
            return false;
        }
    }
}
