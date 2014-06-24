package br.com.tales.sd.server.main.application;

import br.com.tales.sd.server.main.signature.DownloadService;
import br.com.tales.sd.server.main.signature.UploadService;

import javax.swing.*;
import java.nio.channels.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Created by tales on 28/05/14.
 */
public class Server implements IServer, Runnable{

    int port = 2014;

    private String host = "localhost";

    private boolean isRun = false;

    private String name = "ImageService";

    private Server(int port){
        changePort(port);
    }
    private Server(){}


    private static Server INSTANCE = new Server();

    public static Server self(){
        return INSTANCE;
    }

    public  void changePort(int port) {
        if(port != this.port){
            this.port = port;
            System.out.println(LocalizedStrings.self().newPort()+this.port);
        }else{
            System.out.println(LocalizedStrings.self().alreadyInUse());
        }
    }

    @Override
    public void start() {
        Thread t = new Thread(Server.INSTANCE);
        t.start();
    }

    @Override
    public void close() {

    }

    @Override
    public void run() {
        System.out.println(LocalizedStrings.running());

        System.setProperty("java.rmi.server.hostname", host);
        System.setProperty("java.security.policy", "java.policy");

        System.setSecurityManager(new RMISecurityManager());

        try{
            Registry r = LocateRegistry.createRegistry(port);
            try {
                r.bind(name,new Upload());
            } catch (java.rmi.AlreadyBoundException e) {
                e.printStackTrace();
            }

        }catch (RemoteException e){
            e.printStackTrace();
        }
        System.out.println("Realize operacoes");
        while(true){

            Scanner sc = new Scanner(System.in);
            String service = sc.nextLine();
            ServiceManager.self().manager(service);
        }
    }


}
