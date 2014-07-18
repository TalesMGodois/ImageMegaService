import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 28/06/14.
 */

public class Server {
    private String sName = "IndexService";
    private int door = 2018;

    private String ip = "localhost";

    /* My Singleton */
    private Server(){}

    private static Server INSTANCE = new Server();

    public static Server self(){
        return INSTANCE;
    }

    public void start() throws Exception {
        System.out.println("Servidor "+this.sName + " Rodando");

        System.setProperty("java.rmi.server.hostname", ip);
        System.setProperty("java.security.policy", "java.policy");

        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.createRegistry(this.door);
        r.bind(sName,new Index());

        System.out.println("Servidor iniciado...");

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

}
