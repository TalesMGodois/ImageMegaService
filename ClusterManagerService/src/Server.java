import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 14/07/14.
 */
public class Server {


    private int door = 2000;

    private String ip = "localhost";

    private boolean isRun = false;

    private String name = "ImageService";

    private String sName = "ClusterService";


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

        r.bind(sName,new Cluster());

        Object lock = new Object();
        synchronized (lock) {
            lock.wait();
        }
    }

}
