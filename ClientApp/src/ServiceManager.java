/**
 * Created by tales on 28/06/14.
 * package br.com.tales.sd.client;
 **/

import signature.DownloadService;
import signature.UploadService;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
            byte[] img =down.getImage(strs[1]);
            try{
                FileOutputStream fos = new FileOutputStream("/home/tales/Pictures/reset.png");

                fos.write(img) ;

                FileDescriptor fd = fos.getFD();

                fos.flush();

                fd.sync();

                fos.close();
                System.out.println("Download concluido...");
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("Comando não reconhecido");
        }
    }
}