/**
 * Created by tales on 28/06/14.
 * package br.com.tales.sd.client;
 **/

import auxiliar.Put;
import signature.DownloadService;
import signature.UploadService;

import java.io.*;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
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

    public ArrayList<File> listaImagens(File path) {

        ArrayList<File> listImagens = new ArrayList();
        File[] files = path.listFiles();
        for(File file : files ){
            listImagens.add(file);;
        }
        return listImagens;
    }

    public void sendFile(File file) throws RemoteException,NotBoundException,AlreadyBoundException{
        ip = "127.0.1.1";
        door = 1901;
        Put put = new Put(file);
        if(put.getName() != null){
            Registry r = LocateRegistry.getRegistry(ip, door);
            UploadService up = (UploadService) r.lookup(servicesNames.get("upload"));
            boolean isDone = up.make(put);
            if(isDone == true){
                System.out.println("Imagem Enviada Com Sucesso");
            }else{
                System.out.println("Imagem não pode ser enviada, provavel que o servidor esteja fora do ar ou o nome ja exista");
            }
        }else{
            System.out.println("Imagem não pode ser enviada sem nome");
        }


    }

    public File getFile(String name){
        return null;
    }


    public void getFile(){

    }

    public void manager(String str) throws RemoteException,NotBoundException,AlreadyBoundException {
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());

        String[]  strs = str.split(LocalizedStrings.space());
//        ip = "200.137.220.68";
        if(strs[0].equals(LocalizedStrings.put())){
            File file = new File(strs[1]);
            ArrayList<File> files = ServiceManager.self().listaImagens(file);

            try {
                for (int i = 0; i < files.size(); i++) {
                    System.out.println("Criando arquivos e Threads");
                    SendImage ti = new SendImage(files.get(i));
                    Thread t = new Thread(ti);
                    t.start();
                }

            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Imagem não pode ser enviada sem nome");
            }
        }else if(strs[0].equals(LocalizedStrings.get())){
            door = 1902;
            ip = "127.0.1.1";
            Registry r = LocateRegistry.getRegistry(ip, door);
            try{
                try{
                    DownloadService down = (DownloadService) r.lookup(servicesNames.get("download"));
                    byte[] img =down.getImage(strs[1]);
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