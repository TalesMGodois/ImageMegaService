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
import java.util.HashMap;

public class ServiceManager {
    String ip = "localhost";
    int door = 2015;
    String name = "ImageService";

    HashMap<String,String> servicesNames = new HashMap<String, String>();


    private ServiceManager() {
        servicesNames.put("upload","UploadService");
        servicesNames.put("download","DownloadService");
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


        String[]  strs = str.split(LocalizedStrings.space());
        if(strs[0].equals(LocalizedStrings.put())){
            door = 2014;
            Registry r = LocateRegistry.getRegistry(ip, door);
            UploadService up = (UploadService) r.lookup(servicesNames.get("upload"));
            up.make(strs[1]);
            //doUpload
//            Upload.self().upload(strs[1]);
        }else if(strs[0].equals(LocalizedStrings.get())){
            door = 2016;
            Registry r = LocateRegistry.getRegistry(ip, door);
            DownloadService down = (DownloadService) r.lookup(servicesNames.get("download"));
            down.make(strs[1]);
            //doDownload
        }else{
            System.out.println("Comando não reconhecido");
        }
    }
}