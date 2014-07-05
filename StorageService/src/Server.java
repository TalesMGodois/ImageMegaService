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
import java.sql.Connection;
import java.util.HashMap;

public class Server {
    private String sName = "StorageService";
    private int door = 2015;

    private String ip = "localhost";

    private Server(int port){
        this.door = port;
    }

    /* My Singleton */
    private Server(){}

    private static Server INSTANCE = new Server();

    public static Server self(){
        return INSTANCE;
    }

    //Inicia o servidor
//    public void init() {
//        Thread t = new Thread(Server.INSTANCE);
//        t.start();
//    }

    public void start(String userDB,String passwordDB) throws Exception {
        System.out.println("Servidor "+this.sName + " Rodando");

        System.setProperty("java.rmi.server.hostname", ip);
        System.setProperty("java.security.policy", "java.policy");

        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.createRegistry(this.door);
        ConnectionFactory con = new ConnectionFactory();
        con.getConnection(userDB,passwordDB);

        r.bind(sName,new Storage(con));

        System.out.println("Servidor iniciado...");

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

    //Mata o Servidor

}
