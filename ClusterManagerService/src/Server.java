import java.net.InetAddress;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 14/07/14.
 */
public class Server {


    private int door = 2000;

    private String ip = "localhost";

    private int id = 001;

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

        try{
            this.ip = InetAddress.getLocalHost().getHostAddress();

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("CLUSTER "+this.sName+"( "+this.ip +":"+this.door);
        System.out.println("################# CLUSTER INICIADO ######################");
        System.out.println("################# AGUARDANDO CONEXÕES######################");
        System.setProperty("java.rmi.server.hostname", ip);
        System.setProperty("java.security.policy", "java.policy");

        System.setSecurityManager(new RMISecurityManager());

        Registry r = LocateRegistry.createRegistry(this.door);

        Cluster cl = new Cluster();
        r.bind(sName,cl);

        Object lock = new Object();

        synchronized (lock) {
            lock.wait();
        }
    }

}
