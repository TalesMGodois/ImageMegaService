import signature.StorageService;

import java.net.InetAddress;
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
    private static int door = 9002;
    private static String type = "partition";
    private static int id = 0002;
    private static String ip = "localhost";
    private static boolean activated = false;


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

    public void start(String userDB,String passwordDB, int door) throws Exception{
        this.door = door;
        start(userDB,passwordDB);
    }

    public void start(String userDB,String passwordDB) throws Exception {

        try{
            this.ip = InetAddress.getLocalHost().getHostAddress();

        }catch (Exception e){
            e.printStackTrace();
        }



        System.out.println("###############################################################");
        System.out.println("Servidor ("+this.type+")"+ this.sName + "(" + this.id + ") - Porta: " + this.door);

        System.setProperty("java.rmi.server.hostname", ip);
        System.setProperty("java.security.policy", "java.policy");

        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.createRegistry(this.door);
        ConnectionFactory con = new ConnectionFactory();
        con.getConnection(userDB,passwordDB);

        Storage st = new Storage(con);
        r.bind(sName,st);

        boolean b = st.subscribe();

        System.out.println("Servidor iniciado...");

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }
}
