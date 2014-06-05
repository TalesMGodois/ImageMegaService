package br.com.tales.sd.server.main.application;

import br.com.tales.sd.server.main.signature.DownloadService;
import br.com.tales.sd.server.main.signature.UploadService;

import javax.swing.*;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

/**
 * Created by tales on 28/05/14.
 */
public class Server implements IServer, Runnable{

    private int port;

    private String host;

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


    public void choice(int number){
        switch (number){
            case 1:
                Upload.self().start();
                break;
            case 2:
                System.out.println("DOWNLOAD");
//                DownloadService.start();
                break;
        }
    }

    @Override
    public void run() {
        System.out.println(LocalizedStrings.self().running());
        System.setProperty("java.security.policy", "policy.all");
        System.setSecurityManager(new RMISecurityManager());
        while(true){
            System.out.println(LocalizedStrings.self().whattYouDo());
            System.out.println("1- Upload");
            System.out.println("2- Download");
            Scanner sc = new Scanner(System.in);

            try{
                int service = Integer.parseInt(sc.nextLine());
                if(service != 1 && service != 2)
                    System.out.println(LocalizedStrings.self().needANumber());
                else
                    choice(service);

            }catch (NumberFormatException e){
                System.out.println(LocalizedStrings.self().needANumber());
            }
        }
    }
}
