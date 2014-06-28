/**
 * Created by tales on 28/06/14.
 * package br.com.tales.sd.client;
 **/

import signature.DownloadService;
import signature.UploadService;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

 public class ServiceManager {
    String ip = "localhost";
    int door = 2014;
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
        Registry r = LocateRegistry.getRegistry(ip, door);

        String[]  strs = str.split(LocalizedStrings.space());
        if(strs[0].equals(LocalizedStrings.put())){
            UploadService up = (UploadService) r.lookup(name);
            up.make(strs[1]);
            //doUpload
//            Upload.self().upload(strs[1]);
        }else if(strs[0].equals(LocalizedStrings.get())){
            DownloadService down = (DownloadService) r.lookup(name);
            down.make(strs[1]);
            //doDownload
        }else{
            System.out.println("Comando não reconhecido");
        }
    }
}