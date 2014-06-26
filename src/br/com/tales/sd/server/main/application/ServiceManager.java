package br.com.tales.sd.server.main.application;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 08/06/14.
 */
public class ServiceManager {
    String ip = "localhost";
    int door = 2011;
    String name = "ImageService";


    private ServiceManager() {
    }

    private static ServiceManager INSTANCE = new ServiceManager();

    public static ServiceManager self() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceManager();
        }
        return INSTANCE;
    }

    public void manager(String str) throws RemoteException,NotBoundException {
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());
        Registry r = LocateRegistry.getRegistry(ip,door);
        Upload up = (Upload) r.lookup(name);

        String[]  strs = str.split(LocalizedStrings.space());
        if(strs[0].equals(LocalizedStrings.put())){
            up.make("POOOORRA");
            //doUpload
//            Upload.self().upload(strs[1]);
        }else if(strs[0].equals(LocalizedStrings.get())){
            //doDownload
        }else{
            System.out.println("Comando não reconhecido");;
        }
    }

}
