/**
 * Created by tales on 28/06/14.
 * package br.com.tales.sd.client;
 **/

import signature.DownloadService;
import signature.UploadService;

import java.io.*;
import java.net.ConnectException;
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
            try{
                up.make(strs[1]);
            }catch (java.rmi.ConnectException e){
                System.out.println("Servidor " + servicesNames.get("upload") + " possívelmente fora do ar");
            }
            //doUpload
//            Upload.self().upload(strs[1]);
        }else if(strs[0].equals(LocalizedStrings.get())){
            door = 2016;
            Registry r = LocateRegistry.getRegistry(ip, door);
            DownloadService down = (DownloadService) r.lookup(servicesNames.get("download"));

            try{
                try{

                    byte[] img =down.getImage(strs[1]);
                    File image = new File("/home/tales/Pictures/"+ strs[1]+".png");
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(image));
                    bos.write(img);
                    bos.close();
                    System.out.println("Download concluido...");
                }catch (java.rmi.ConnectException e){
                    System.out.println("Servidor" + servicesNames.get("download")+" não respondendo");
                }

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