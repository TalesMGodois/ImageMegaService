/**
 * Created by tales on 28/06/14.
 * package br.com.tales.sd.client;
 **/

import signature.DownloadService;
import signature.UploadService;

import java.io.*;
import java.net.ConnectException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public void manager(String str) throws RemoteException,NotBoundException,AlreadyBoundException {
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());



        String[]  strs = str.split(LocalizedStrings.space());

        if(strs[0].equals(LocalizedStrings.put())){
            door = 1901;
            Registry r = LocateRegistry.getRegistry(ip, door);
            try{
                UploadService up = (UploadService) r.lookup(servicesNames.get("upload"));
                boolean isDone = up.make(strs);
                if(isDone == true){
                    System.out.println("Imagem Enviada Com Sucesso");
                }else{
                    System.out.println("Imagem Não Enviada");
                }

            }catch (java.rmi.ConnectException e){
                System.out.println("Servidor " + servicesNames.get("upload") + " possívelmente fora do ar");
            }
        }else if(strs[0].equals(LocalizedStrings.get())){
            door = 1902;
            Registry r = LocateRegistry.getRegistry(ip, door);
            try{
                try{
                    DownloadService down = (DownloadService) r.lookup(servicesNames.get("download"));
                    byte[] img =down.getImage(strs);
                    File image = new File("/home/tales/Pictures/"+ strs[1]+".png");
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(image));
                    bos.write(img);
                    bos.close();
                    System.out.println("Download concluido...");
                }catch (java.rmi.ConnectException e){
                    System.out.println("Servidor" + servicesNames.get("download")+" não respondendo");
                }catch (NullPointerException e){
                    System.out.println("Imagem não encontrada");
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