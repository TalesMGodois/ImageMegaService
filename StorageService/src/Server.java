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
    private static String sName = "StorageService";
    private static int door = 2015;
    private static String type = "partition";
    private static int id = 1;
    private static String ip = "localhost";


    public static String getType() {
        return type;
    }

    public static int getId() {
        return id;
    }

    public static String getIp() {
        return ip;
    }

    /* My Singleton */
    private Server(){}

    private static Server INSTANCE = new Server();

    public static Server self(){
        return INSTANCE;
    }

    public static int getDoor(){
        return door;
    }

    public static String getName(){
        return sName;
    }

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

        Storage st = new Storage();

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

}
