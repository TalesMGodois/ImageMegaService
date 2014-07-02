//package br.com.tales.sd.server.main.application;
//
//import signature.DownloadService;
//import br.com.tales.sd.server.main.signature.UploadService;
//
//import javax.swing.*;
//import java.nio.channels.AlreadyBoundException;
//import java.rmi.RMISecurityManager;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.util.Scanner;
//
///**
// * Created by tales on 28/05/14.
// */
//public class Server implements IServer, Runnable{
//
//    int door = 2014;
//
//    private String ip = "localhost";
//
//    private boolean isRun = false;
//
//    private String name = "ImageService";
//
//
//    private Server(int port){
//        changePort(port);
//    }
//
///* My Singleton */
//    private Server(){}
//
//
//    private static Server INSTANCE = new Server();
//
//    public static Server self(){
//        return INSTANCE;
//    }
///*My Singelton */
//
//    //Metodo para alterar porta em que o servidor está rodando
//    public  void changePort(int port) {
//        if(port != this.door){
//            this.door = port;
//            System.out.println(LocalizedStrings.self().newPort()+this.door);
//        }else{
//            System.out.println(LocalizedStrings.self().alreadyInUse());
//        }
//    }
//
//
//    //Inicia o servidor
//    @Override
//    public void init() {
//        Thread t = new Thread(Server.INSTANCE);
//        t.start();
//    }
//
//    @Override
//    public void start() throws Exception {
//        System.out.println(LocalizedStrings.running());
//
//        System.setProperty("java.rmi.server.hostname", ip);
//        System.setProperty("java.security.policy", "java.policy");
//
//        System.setSecurityManager(new RMISecurityManager());
//
//        Registry r = LocateRegistry.createRegistry(door);
//
//        r.bind(name,new Upload());
//
//        System.out.println("Servidor iniciado...");
//
//        Object lock = new Object();
//        synchronized (lock) {
//            lock.wait();
//        }
//    }
//
//    //Mata o Servidor
//    @Override
//    public void close() {
//
//    }
//    //o Proprio server
//    @Override
//    public void run()  {
//        try{
//            start();
//        }catch (Exception e){
//            System.out.println("Não foi possível iniciar o server");
//            e.printStackTrace();
//        }
//
//    }
//
//}
