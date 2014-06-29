/**
 * Created by tales on 28/06/14.
 */


import com.sun.webpane.platform.LocalizedStrings;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server implements  Runnable{

    private int door = 2014;

    private String ip = "localhost";

    private boolean isRun = false;

    private String name = "ImageService";

    private String sName = "UploadService";

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

        r.bind(sName,new Upload());

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
