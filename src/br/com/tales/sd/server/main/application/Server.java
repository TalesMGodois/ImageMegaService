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

    private boolean isRun = false;

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


    public void serviceManager(String number){
        String[]  str = number.split(LocalizedStrings.space());

//        if(str[0] == "put"){
//            Upload.self().upload();
//        }
//        switch (number){
//            case "put":
//                Upload.self().start();
//                break;
//            case "get":
//                System.out.println("DOWNLOAD");
////                DownloadService.start();
//                break;
//        }
    }

    @Override
    public void run() {
        System.out.println(LocalizedStrings.running());
        System.setProperty("java.security.policy", "policy.all");
        System.setSecurityManager(new RMISecurityManager());
        System.out.println("Realize operacoes");
        while(true){

            Scanner sc = new Scanner(System.in);

            try{
                String service = sc.nextLine();
                serviceManager(service);
//                if(service != 1 && service != 2)
//                    System.out.println(LocalizedStrings.self().needANumber());
//                else

            }catch (NumberFormatException e){
                System.out.println(LocalizedStrings.needANumber());
            }
        }
    }


}
