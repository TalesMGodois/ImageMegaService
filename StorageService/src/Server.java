import signature.StorageService;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 28/06/14.
 */

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class Server implements  Runnable{
    private String sName = "StorageService";
    private int door = 2015;

    private String ip = "localhost";

    private Server(int port){
        this.door = port;

//        changePort(port);
    }

    /* My Singleton */
    private Server(){}


    private static Server INSTANCE = new Server();

    public static Server self(){
        return INSTANCE;
    }

    //Inicia o servidor
    public void init() {
        Thread t = new Thread(Server.INSTANCE);
        t.start();
    }

    public void start() throws Exception {
        System.out.println("Servidor "+this.sName + " Rodando");

        System.setProperty("java.rmi.server.hostname", ip);
        System.setProperty("java.security.policy", "java.policy");

        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.createRegistry(this.door);

        r.bind(sName,new Storage());

        System.out.println("Servidor iniciado...");

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

    //Mata o Servidor
    public void close() {

    }
    //o Proprio server
    @Override
    public void run()  {
        try{
            start();
        }catch (Exception e){
            System.out.println("Não foi possível iniciar o server");
            e.printStackTrace();
        }
    }
}